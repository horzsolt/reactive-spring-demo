To install httpie follow the instructions here:
https://www.hanselman.com/blog/InstallingHTTPIEHTTPForHumansOnWindowsGreatForASPNETWebAPIAndRESTfulJSONServices.aspx


e:\SPRING5>http :8080/movies
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
transfer-encoding: chunked

[
    {
        "id": "5a2d5e4f60b56a27b49a2266",
        "title": "Silence of Lambdas"
    },
    {
        "id": "5a2d5e4f60b56a27b49a2268",
        "title": "Back to the Future"
    },
    {
        "id": "5a2d5e4f60b56a27b49a2267",
        "title": "Y to Mono Tumbien"
    },
    {
        "id": "5a2d5e4f60b56a27b49a2269",
        "title": "AEon Flux"
    }
]


e:\SPRING5>http :8080/movies/5a2d5e4f60b56a27b49a2266
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
transfer-encoding: chunked

{
    "id": "5a2d5e4f60b56a27b49a2266",
    "title": "Silence of Lambdas"
}


e:\SPRING5>http -S :8080/movies/5a2d5e4f60b56a27b49a2266/events
HTTP/1.1 200 OK
Content-Type: text/event-stream
transfer-encoding: chunked

data:{"movieId":"5a2d5e4f60b56a27b49a2266","date":"2017-12-10T16:40:22.241+0000"}

data:{"movieId":"5a2d5e4f60b56a27b49a2266","date":"2017-12-10T16:40:23.242+0000"}

data:{"movieId":"5a2d5e4f60b56a27b49a2266","date":"2017-12-10T16:40:24.244+0000"}



e:\SPRING5>