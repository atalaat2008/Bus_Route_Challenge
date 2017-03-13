package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import model.BusRoute;

@Service
@Component
public class BusService {

	private Hashtable<String, String> busRoutes;
	private int numberOfRoutes;

	@Autowired
	public BusService(ApplicationArguments args) {
		busRoutes = new Hashtable<String, String>();
		numberOfRoutes = 0;
		String[] arguData = args.getSourceArgs();
		String filename = "";
		BufferedReader br = null;
		FileReader fr = null;
		for (int i = 0; i < arguData.length; i++) {
			filename = arguData[i];
		}
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String sCurrentLine;
			int lineIndex = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				if (lineIndex == 0) {
					numberOfRoutes = Integer.parseInt(sCurrentLine);
				} else {
					String[] lineSplitter = sCurrentLine.split(" ");
					busRoutes.put(lineSplitter[0], sCurrentLine.substring(lineSplitter[0].length()));
				}
				lineIndex++;
			}
			System.out.println(busRoutes.size());
			System.out.println(numberOfRoutes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BusRoute getBusRouteInfo(String dep_sid, String arr_sid) {
		BusRoute busRoute = new BusRoute();
		Set<String> keys = busRoutes.keySet();
		busRoute.setArr_sid(arr_sid);
		busRoute.setDep_sid(dep_sid);
		busRoute.setDirect_bus_route(false);
		for (String key : keys) {
			String busRouteStr = busRoutes.get(key);
			String[] busRouteSplit = busRouteStr.split(" ");

			if (Arrays.asList(busRouteSplit).contains(arr_sid) && Arrays.asList(busRouteSplit).contains(dep_sid)) {
				busRoute.setDirect_bus_route(true);
			}
		}
		return busRoute;
	}
}
