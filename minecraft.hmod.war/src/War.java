

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class War extends Plugin {
	
	public static final int LIFEPOOL = 3;
	private WarListener listener = new WarListener(this);
    private Logger log;
    String name = "War";
    String version = "0.1";
    
    
    private final List<Warzone> warzones = new ArrayList<Warzone>();

	public void initialize() {
		this.log = Logger.getLogger("Minecraft");
		getLogger().info(name + " " + version + " initialized");
		
		etc.getLoader().addListener(
                PluginLoader.Hook.COMMAND,
                listener,
                this,
                PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener( PluginLoader.Hook.LOGIN,  
				listener,
				this,
				PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener( PluginLoader.Hook.HEALTH_CHANGE,  
				listener,
				this,
				PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener( PluginLoader.Hook.DAMAGE,  
				listener,
				this,
				PluginListener.Priority.HIGH);
		etc.getLoader().addListener( PluginLoader.Hook.PLAYER_MOVE,  
				listener,
				this,
				PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener( PluginLoader.Hook.DISCONNECT,  
				listener,
				this,
				PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener( PluginLoader.Hook.IGNITE,  
				listener,
				this,
				PluginListener.Priority.MEDIUM);
		etc.getLoader().addListener( PluginLoader.Hook.FLOW,  
				listener,
				this,
				PluginListener.Priority.MEDIUM);
		
		
//        etc.getLoader().addListener(
//                PluginLoader.Hook.BLOCK_CREATED,
//                listener,
//                this,
//                PluginListener.Priority.MEDIUM);
	}
	
	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	public Team getPlayerTeam(String playerName) {
		for(Warzone warzone : warzones) {
			Team team = warzone.getPlayerTeam(playerName);
			if(team != null) return team;
		}
		return null;
	}
	
	public Warzone getPlayerWarzone(String playerName) {
		for(Warzone warzone : warzones) {
			Team team = warzone.getPlayerTeam(playerName);
			if(team != null) return warzone;
		}
		return null;
	}
	

	public Logger getLogger() {
		return log;
	}
	
	public Warzone warzone(Location location) {
		for(Warzone warzone : warzones) {
			if(warzone.contains(location)) return warzone;
		}
		return null;
	}

	public boolean inAnyWarzone(Location location) {
		if(warzone(location) == null) {
			return false;
		}
		return true;
	}
	
	public boolean inWarzone(String warzoneName, Location location) {
		Warzone currentZone = warzone(location);
		if(currentZone == null) {
			return false;
		} else if (warzoneName.equals(currentZone.getName())){
			return true;
		}
		return false;
	}

	public void addWarzone(Warzone zone) {
		warzones.add(zone);
	}

	public Server getServer() {
		// TODO Auto-generated method stub
		return etc.getServer();
	}

	public List<Warzone> getWarzones() {
		return warzones;
	}
	
	public String str(String str) {
		String out = Colors.LightGray + "[war] " + Colors.White + str;
		return out;
	}
	
	public Warzone findWarzone(String warzoneName) {
		for(Warzone warzone : warzones) {
			if(warzone.getName().equals(warzoneName)) {
				return warzone;
			}
		}
		return null;
	}

}