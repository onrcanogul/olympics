# Olympic Medal Ranking API - Test Flow

## 1. Country oluştur (JSON body)

```bash
curl -s -X POST http://localhost:8080/api/country -H 'Content-Type: application/json' -d '{"name":"Türkiye","code":"TUR"}'
curl -s -X POST http://localhost:8080/api/country -H 'Content-Type: application/json' -d '{"name":"United States","code":"USA"}'
curl -s -X POST http://localhost:8080/api/country -H 'Content-Type: application/json' -d '{"name":"Japan","code":"JPN"}'
```

## 2. Sport oluştur (query param)

```bash
curl -s -X POST "http://localhost:8080/api/sport?name=Archery"
curl -s -X POST "http://localhost:8080/api/sport?name=Swimming"
curl -s -X POST "http://localhost:8080/api/sport?name=Judo"
```

## 3. Athlete oluştur (query param)

```bash
curl -s -X POST "http://localhost:8080/api/athletes?name=Mete%20Gazoz&countryId=<TUR_ID>"
curl -s -X POST "http://localhost:8080/api/athletes?name=Noah%20Lyles&countryId=<USA_ID>"
curl -s -X POST "http://localhost:8080/api/athletes?name=Abby%20Steuter&countryId=<USA_ID>"
curl -s -X POST "http://localhost:8080/api/athletes?name=Hifumi%20Abe&countryId=<JPN_ID>"
```

## 4. Madalya kaydet (case sample data)

```bash
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<ARCHERY_ID>&athleteId=<METE_ID>&medalTypes=GOLD"
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<ARCHERY_ID>&athleteId=<METE_ID>&medalTypes=SILVER"
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<SWIMMING_ID>&athleteId=<NOAH_ID>&medalTypes=GOLD"
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<SWIMMING_ID>&athleteId=<NOAH_ID>&medalTypes=BRONZE"
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<JUDO_ID>&athleteId=<ABE_ID>&medalTypes=GOLD"
```

```bash
curl -s http://localhost:8080/api/country/ranking
curl -s http://localhost:8080/api/sport/ranking
curl -s http://localhost:8080/api/athletes/ranking
```

## 6. Tie-break senaryosu

Abby'ye üç bronz ekle (aynı komut üç kez):

```bash
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<JUDO_ID>&athleteId=<ABBY_ID>&medalTypes=BRONZE"
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<JUDO_ID>&athleteId=<ABBY_ID>&medalTypes=BRONZE"
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<JUDO_ID>&athleteId=<ABBY_ID>&medalTypes=BRONZE"
```

```bash
curl -s http://localhost:8080/api/athletes/ranking
curl -s http://localhost:8080/api/sport/ranking
curl -s http://localhost:8080/api/country/ranking
```

## 7. Hata senaryoları

### 404 Not Found

```bash
# Country Not Found
curl -s -X POST "http://localhost:8080/api/athletes?name=Ghost&countryId=00000000-0000-0000-0000-000000000000"

# Sport Not Found
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=00000000-0000-0000-0000-000000000000&athleteId=<METE_ID>&medalTypes=GOLD"

# Athlete Not Found
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<ARCHERY_ID>&athleteId=00000000-0000-0000-0000-000000000000&medalTypes=GOLD"
```

### 400 Bad Request

```bash
# Boş country body
curl -s -X POST http://localhost:8080/api/country -H 'Content-Type: application/json' -d '{}'

# Boş sport ismi
curl -s -X POST "http://localhost:8080/api/sport?name="

# Boş athlete ismi
curl -s -X POST "http://localhost:8080/api/athletes?name=&countryId=<TUR_ID>"

# medalTypes eksik
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<ARCHERY_ID>&athleteId=<METE_ID>"

# Geçersiz enum
curl -s -X POST "http://localhost:8080/api/medal-record?sportId=<ARCHERY_ID>&athleteId=<METE_ID>&medalTypes=PLATINUM"
```

### Hata cevabı formatı

```json
{
  "timestamp": "2026-10-09T19:03:47.493617",
  "status": 404,
  "error": "NOT_FOUND",
  "message": "Country Not Found",
  "path": "/api/athletes"
}
```