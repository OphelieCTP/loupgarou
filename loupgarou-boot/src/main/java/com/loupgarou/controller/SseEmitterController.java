package com.loupgarou.controller; 
 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.List; 
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter; 
 
@Controller
@CrossOrigin("*")
public class SseEmitterController { 
	List<SseEmitter> emitters = new ArrayList<SseEmitter>(); 
 
	@GetMapping("/sse") 
	public SseEmitter handleSse() { 
		SseEmitter emitter = new SseEmitter(); 
		 
		emitters.add(emitter); 
 
		return emitter; 
	} 
 
	@GetMapping("/envoyermessage") 
	@ResponseBody 
	public String handleMessage(@RequestParam String message) { 
		 
		for (SseEmitter e : emitters) { 
			try { 
				e.send(message); 
			} catch (IOException e1) { 
				e1.printStackTrace(); 
			} 
		} 
		 
		return "coucou"; 
	} 
} 
