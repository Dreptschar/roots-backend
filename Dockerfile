#Build
FROM golang:1.24-alpine as builder
WORKDIR /app
COPY . .
RUN go build -o server .

#Finally
FROM alpine:latest
WORKDIR /app
COPY --from=builder /app/server .

EXPOSE 8080
CMD ["./server"]