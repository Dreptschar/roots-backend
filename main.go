package main

import (
	server "github.com/Dreptschar/roots-backend/server"
	"log"
	"net/http"
)

func main() {
	s := server.NewRootsServer()
	log.Fatal(http.ListenAndServe(":8080", s))
}
