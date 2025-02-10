package com.example.trafikk.data.entity

import android.database.Cursor
import android.util.Log
import com.example.trafikk.data.datasource.VeritabaniYardimcisi
import com.example.trafikk.data.entity.Sorular

class SorularDao {

    fun veritabaniniKontrolEt(vt: VeritabaniYardimcisi) {
        val db = vt.readableDatabase
        try {
            val c = db.rawQuery("SELECT * FROM sorular", null)
            Log.d("SorularDao", "Toplam kayıt sayısı: ${c.count}")

            while (c.moveToNext()) {
                val id = c.getInt(c.getColumnIndexOrThrow("soru_id"))
                val ad = c.getString(c.getColumnIndexOrThrow("soru_ad"))
                val resim = c.getString(c.getColumnIndexOrThrow("soru_resim"))
                Log.d("SorularDao", "Soru: id=$id, ad=$ad, resim=$resim")
            }
            c.close()
        } catch (e: Exception) {
            Log.e("SorularDao", "Kontrol hatası: ${e.message}")
        } finally {
            db.close()
        }
    }

    fun rastgele10BayrakGetir(vt: VeritabaniYardimcisi): ArrayList<Sorular> {
        val sorularListe = ArrayList<Sorular>()
        val kullanilmisSorular = HashSet<Int>()
        val db = vt.writableDatabase

        try {
            // Toplam soru sayısını kontrol et
            val countCursor = db.rawQuery("SELECT COUNT(*) FROM sorular", null)
            var toplamSoru = 0
            if (countCursor.moveToFirst()) {
                toplamSoru = countCursor.getInt(0)
            }
            countCursor.close()

            // Yeterli soru yoksa mevcut soruları döndür
            if (toplamSoru < 15) {
                Log.w("SorularDao", "Veritabanında yeterli soru yok: $toplamSoru")
                val c = db.rawQuery("SELECT * FROM sorular ORDER BY RANDOM()", null)
                while (c.moveToNext()) {
                    val soru = Sorular(
                        c.getInt(c.getColumnIndexOrThrow("soru_id")),
                        c.getString(c.getColumnIndexOrThrow("soru_ad"))?.trim() ?: continue,
                        c.getString(c.getColumnIndexOrThrow("soru_resim"))?.trim() ?: continue
                    )
                    sorularListe.add(soru)
                }
                c.close()
                return sorularListe
            }

            // 10 benzersiz rastgele soru al
            while (sorularListe.size < 15) {
                val notInClause = if (kullanilmisSorular.isEmpty()) "0" else kullanilmisSorular.joinToString(",")
                val c = db.rawQuery("""
                    SELECT * FROM sorular 
                    WHERE soru_id NOT IN ($notInClause)
                    ORDER BY RANDOM() 
                    LIMIT 1
                """, null)

                if (c.moveToFirst()) {
                    val id = c.getInt(c.getColumnIndexOrThrow("soru_id"))
                    val ad = c.getString(c.getColumnIndexOrThrow("soru_ad"))?.trim() ?: continue
                    val resim = c.getString(c.getColumnIndexOrThrow("soru_resim"))?.trim() ?: continue

                    if (ad.isNotEmpty() && resim.isNotEmpty() && !kullanilmisSorular.contains(id)) {
                        val soru = Sorular(id, ad, resim)
                        sorularListe.add(soru)
                        kullanilmisSorular.add(id)
                        Log.d("SorularDao", "Soru eklendi: id=$id, ad=$ad, resim=$resim")
                    }
                }
                c.close()
            }

        } catch (e: Exception) {
            Log.e("SorularDao", "Veritabanı hatası: ${e.message}")
        } finally {
            db.close()
        }

        return sorularListe
    }

    fun rastgele3YanlisSecenekGetir(vt: VeritabaniYardimcisi, soru_id: Int): ArrayList<Sorular> {
        val sorularListe = ArrayList<Sorular>()
        val kullanilmisSorular = HashSet<Int>() // Kullanılan soruları takip et
        kullanilmisSorular.add(soru_id) // Doğru cevabı ekle
        val db = vt.writableDatabase

        try {
            // Toplam soru sayısını kontrol et
            val countCursor = db.rawQuery("SELECT COUNT(*) FROM sorular WHERE soru_id != ?",
                arrayOf(soru_id.toString()))
            var toplamSoru = 0
            if (countCursor.moveToFirst()) {
                toplamSoru = countCursor.getInt(0)
            }
            countCursor.close()

            // 3 benzersiz yanlış seçenek al
            while (sorularListe.size < 3 && sorularListe.size < toplamSoru) {
                val c = db.rawQuery("""
                    SELECT * FROM sorular 
                    WHERE soru_id NOT IN (${kullanilmisSorular.joinToString(",")})
                    ORDER BY RANDOM() 
                    LIMIT 1
                """, null)

                if (c.moveToFirst()) {
                    val id = c.getInt(c.getColumnIndexOrThrow("soru_id"))
                    val ad = c.getString(c.getColumnIndexOrThrow("soru_ad"))?.trim() ?: continue
                    val resim = c.getString(c.getColumnIndexOrThrow("soru_resim"))?.trim() ?: continue

                    if (ad.isNotEmpty() && resim.isNotEmpty() && !kullanilmisSorular.contains(id)) {
                        val soru = Sorular(id, ad, resim)
                        sorularListe.add(soru)
                        kullanilmisSorular.add(id)
                        Log.d("SorularDao", "Yanlış seçenek eklendi: id=$id, ad=$ad")
                    }
                }
                c.close()
            }

        } catch (e: Exception) {
            Log.e("SorularDao", "Yanlış seçenek getirme hatası: ${e.message}")
        } finally {
            db.close()
        }

        return sorularListe
    }
}