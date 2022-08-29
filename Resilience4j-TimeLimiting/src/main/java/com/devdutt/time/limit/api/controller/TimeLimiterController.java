package com.devdutt.time.limit.api.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
public class TimeLimiterController {

	Logger logger = LoggerFactory.getLogger(TimeLimiterController.class);

	@GetMapping("/getMessageTL")
	@TimeLimiter(name = "getMessageTL")
	public CompletableFuture<String> getMessage() {
		return CompletableFuture.supplyAsync(this::getResponse);
	}

	private String getResponse() {
		if (Math.random() < 0.4) {     //Expected to fail 40% of the time
			return "Executing with in the Time limit.....";
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		return "Exception due to Request Timeout.";
	}

}
