package controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.BusRoute;
import service.BusService;

@RestController
@RequestMapping("/api")
public class BusController {
	@Autowired
	BusService ps;
	

	
	@RequestMapping("/direct")
	public BusRoute checkIfDirectRoute(@PathParam("dep_sid") String dep_sid,@PathParam("arr_sid") String arr_sid)
	{

		return ps.getBusRouteInfo(dep_sid, arr_sid);
	}
	
}
