package main

import (
	"fmt"
	"net/http"
	"strings"
	"sync"
	"time"

	"go.uber.org/ratelimit"
)

const (
	maxIdleConnections = 20
	requestTimeout     = 20
	baseUrl  = "http://localhost:8010"
	rateLimit = 150
)

var httpClient *http.Client
var wg sync.WaitGroup

func createHTTPClient() *http.Client {
	return &http.Client{
		Transport: &http.Transport{
			MaxIdleConnsPerHost: maxIdleConnections,
		},
		Timeout: time.Duration(requestTimeout) * time.Second,
	}
}

func init() {
	httpClient = createHTTPClient()
}

func callSleep() {
	defer wg.Done()
	t1 := time.Now()
	url := fmt.Sprintf("%s/v1/users/sleep", baseUrl)
	method := "GET"

	payload := strings.NewReader(``)

	req, err := http.NewRequest(method, url, payload)

	if err != nil {
		fmt.Println(err)
		return
	}

	res, err := httpClient.Do(req)
	if err != nil {
		fmt.Println(err, res)
		return
	}
	defer res.Body.Close()
	t2 := time.Now()
	fmt.Println("Time taken for sleep", t2.Sub(t1))
}


func Driver() {
	rl := ratelimit.New(rateLimit)
	for i := 0; i < 100000; i++ {
		rl.Take()
		wg.Add(1)
		go callSleep()
	}
	wg.Wait()
	fmt.Println("Completed")
}

func main() {
    Driver()
}
