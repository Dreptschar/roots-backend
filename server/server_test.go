package server

import (
	"net/http"
	"net/http/httptest"
	"testing"
)

func TestServer(t *testing.T) {
	t.Run("Status endpoint returns status", func(t *testing.T) {
		server := NewRootsServer()

		req, _ := http.NewRequest("GET", "/status", nil)
		resp := httptest.NewRecorder()
		server.ServeHTTP(resp, req)

		want := "STATUS"
		got := resp.Body.String()
		if got != want {
			t.Errorf("got %q, want %q", got, want)
		}
	})
}
