Ścieżka do skryptu bazy danych: credit/src/main/resources/dbscript

Przykłady wywołań:
1. getCredit
Metoda: Get
ścieżka do zasobu: /credits

Przykładowy request: brak

Przykładowy response: 

{

    "credits": [
        {
            "credit": {
                "creditId": 1,
                "creditName": "Testowy kredyt",
                "value": 20000.00
            },
            "customer": {
                "firstName": "Jan",
                "lastName": "Kowalski",
                "pesel": "77012111111"
            }
        },
        {
            "credit": {
                "creditId": 2,
                "creditName": "Testowy kredyt 2",
                "value": 200000.00
            },
            "customer": {
                "firstName": "Zygmunt",
                "lastName": "Nowak",
                "pesel": "75051423456"
            }
        },
        {
            "credit": {
                "creditId": 3,
                "creditName": "Testowy kredyt 3",
                "value": 40000.00
            },
            "customer": {
                "firstName": "Zdzislaw",
                "lastName": "Pawlak",
                "pesel": "80101077777"
            }
        }
    ]
    
}




2. createCredit
Metoda: Post
ścieżka do zasobu: /credit


Przykładowy request:

{

            "credit": {
                "creditName": "Testowy kredyt 3",
                "value": 40000.00
            },
            "customer": {
                "firstName": "Zdzislaw",
                "lastName": "Pawlak",
                "pesel": "80101077777"
            }
}

Przykładowy response:

{

    "creditId": 3
}
