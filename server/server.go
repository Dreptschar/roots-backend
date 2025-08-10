package server

import (
	"github.com/Dreptschar/roots-backend/handlers"
	"net/http"
)

type RootsServer struct {
	http.Handler
}

func NewRootsServer() *RootsServer {
	p := new(RootsServer)

	router := http.NewServeMux()
	router.Handle("/status", http.HandlerFunc(handlers.StatusHandler))

	p.Handler = router
	return p
}
