package com.tommytony.war.jobs;

import bukkit.tommytony.war.War;

import com.tommytony.war.Warzone;
import com.tommytony.war.mappers.WarzoneMapper;

public class RestoreWarzonesJob implements Runnable {

	private final War war;
	private final String warzonesStr;
	private final boolean newWarInstall;

	public RestoreWarzonesJob(War war, String warzonesStr, boolean newWarInstall) {
		this.war = war;
		this.warzonesStr = warzonesStr;
		this.newWarInstall = newWarInstall;
	}

	public void run() {
		String[] warzoneSplit = warzonesStr.split(",");
		war.getWarzones().clear();
		for(String warzoneName : warzoneSplit) {
			if(warzoneName != null && !warzoneName.equals("")){
				war.logInfo("Restoring zone " + warzoneName + "...");
				Warzone zone = WarzoneMapper.load(war, warzoneName, !newWarInstall);		// cascade load, only load blocks if warzone exists
				if(zone != null) { // could have failed, would've been logged already 
					war.getWarzones().add(zone);
					zone.getVolume().resetBlocks();
					if(zone.getLobby() != null) {
						zone.getLobby().getVolume().resetBlocks();
					}
					zone.initializeZone();
				}
			}
		}
		war.logInfo("Warzones ready.");
	}

}
