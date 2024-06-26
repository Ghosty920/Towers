package me.ghosty.towers.features.config;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// list<?>, color, colorList, doubleList, floatList, intList, long, longList, mapList, byteList
public class Configuration {
	
	@Getter
	private final FileConfiguration yaml;
	
	public Configuration(String path) {
		File f = new File(path);
		if(f.isDirectory()) throw new RuntimeException("The configuration \""+path+"\" isn't a file.");
		if(!f.exists()) throw new RuntimeException("The configuration \""+path+"\" doesn't exist.");
		yaml = YamlConfiguration.loadConfiguration(f);
	}
	
	public Configuration(FileConfiguration config) {
		yaml = config;
	}
	
	/**
	 * Get a {@link Integer} from the chosen config at the decided path.
	 * @param path Where the {@link Integer} is.
	 * @return The {@link Integer} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't an Integer.
	 */
	public Integer getInt(String path) {
		check(path, "Integer", () -> yaml.isInt(path));
		return yaml.getInt(path);
	}
	
	/**
	 * Get a {@link Long} from the chosen config at the decided path.
	 * @param path Where the {@link Long} is.
	 * @return The {@link Long} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't a Long.
	 */
	public Long getLong(String path) {
		check(path, "Long", () -> yaml.isLong(path));
		return yaml.getLong(path);
	}
	
	/**
	 * Get a {@link Short} from the chosen config at the decided path.
	 * @param path Where the {@link Short} is.
	 * @return The {@link Short} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't a Short.
	 * @throws NumberFormatException Thrown if the double cannot be converted into a Short.
	 */
	public Short getShort(String path) {
		check(path, "Integer (Short)", () -> yaml.isInt(path));
		return (short) yaml.getInt(path);
	}
	
	/**
	 * Get a {@link Double} from the chosen config at the decided path.
	 * @param path Where the {@link Double} is.
	 * @return The {@link Double} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't a Double.
	 */
	public Double getDouble(String path) {
		check(path, "Double", () -> yaml.isDouble(path));
		return yaml.getDouble(path);
	}
	
	/**
	 * Get a {@link Float} from the chosen config at the decided path.
	 * @param path Where the {@link Float} is.
	 * @return The {@link Float} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't a Double.
	 * @throws NumberFormatException Thrown if the double cannot be converted into a Float.
	 */
	public Float getFloat(String path) {
		check(path, "Double (Float)", () -> yaml.isDouble(path));
		return (float) yaml.getDouble(path);
	}
	
	/**
	 * Get a {@link Boolean} from the chosen config at the decided path.
	 * @param path Where the {@link Boolean} is.
	 * @return The {@link Boolean} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't a Boolean.
	 */
	public Boolean getBoolean(String path) {
		FileConfiguration yaml = getYaml();
		check(path, "Boolean", () -> yaml.isBoolean(path));
		return yaml.getBoolean(path);
	}
	
	/**
	 * Get a {@link ArrayList}<{@link Boolean}> from the chosen config at the decided path.
	 * @param path Where the {@link List}<{@link Boolean}> is.
	 * @return The {@link List}<{@link Boolean}> (casted into a {@link ArrayList}) at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't a list.
	 */
	public ArrayList<Boolean> getBooleanList(String path) {
		check(path, "List (Booleans)", () -> yaml.isList(path));
		return new ArrayList<>(yaml.getBooleanList(path));
	}
	
	/**
	 * Get a {@link Object} from the chosen config at the decided path.
	 * @param path Where the {@link Object} is.
	 * @return The {@link Object} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 */
	public Object getObject(String path) {
		check(path, "", () -> true);
		return yaml.get(path);
	}
	
	/**
	 * Get a {@link ArrayList} from the chosen config at the decided path.
	 * @param path Where the {@link List} is.
	 * @return The {@link List} (casted into a {@link ArrayList}) at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't a list.
	 */
	public ArrayList<?> getList(String path) {
		check(path, "List", () -> yaml.isList(path));
		return new ArrayList<>(yaml.getList(path));
	}
	
	/**
	 * Get a {@link ItemStack} from the chosen config at the decided path.
	 * @param path Where the {@link ItemStack} is.
	 * @return The {@link ItemStack} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't an ItemStack.
	 */
	public ItemStack getItemStack(String path) {
		check(path, "ItemStack", () -> yaml.isItemStack(path));
		return yaml.getItemStack(path);
	}
	
	/**
	 * Get a {@link String} from the chosen config at the decided path.
	 * @param path Where the {@link String} is.
	 * @return The {@link String} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 */
	public String getString(String path) {
		check(path, "", () -> true);
		return yaml.getString(path);
	}
	
	/**
	 * Get a {@link ArrayList}<{@link String}> from the chosen config at the decided path.
	 * @param path Where the {@link List}<{@link String}> is.
	 * @return The {@link List}<{@link String}> (casted into a {@link ArrayList}) at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't a list.
	 */
	public ArrayList<String> getStringList(String path) {
		check(path, "List (String)", () -> yaml.isList(path));
		return new ArrayList<>(yaml.getStringList(path));
	}
	
	/**
	 * Get a {@link Location} from the chosen config at the decided path.
	 * @param path Where the {@link Location} is.
	 * @return The {@link Location} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't a .
	 * @throws NullPointerException Thrown if the world doesn't exist
	 */
	//TODO Find a better way to do it maybe
	public Location getLocation(String path, World world) {
		FileConfiguration yaml = getYaml();
		check(path, "", () -> true);
		
		World wd = world;
		double x = getDouble(path+".x"), y = getDouble(path+".y"), z = getDouble(path+".z");
		float yaw = getFloat(path+".yaw"), pitch = getFloat(path+".pitch");
		
		if(wd == null) {
			check(path+".world", "World (String)", () -> yaml.isString(path));
			wd = Bukkit.getWorld(yaml.getString(path+".world"));
			if(wd == null) {
				throw new NullPointerException("The world at \""+path+".world\" in plugins/Towers/config.yml doesn't exist.");
			}
		}
		return new Location(wd, x, y, z, yaw, pitch);
	}
	
	/**
	 * Get a {@link OfflinePlayer} from the chosen config at the decided path.
	 * @param path Where the {@link OfflinePlayer} is.
	 * @return The {@link OfflinePlayer} at the path.
	 * @throws InvalidPathException Thrown if there is no object at this path.
	 * @throws IllegalFormatException Thrown if the caught object isn't an OfflinePlayer.
	 */
	public OfflinePlayer get(String path) {
		check(path, "OfflinePlayer", () -> yaml.isOfflinePlayer(path));
		return yaml.getOfflinePlayer(path);
	}
	
	protected void check(String path, String type, Supplier<Boolean> typeCheck) {
		if(!yaml.contains(path)) {
			if(yaml.getDefaults().contains(path)) {
				yaml.set(path, yaml.getDefaults().get(path));
				throw new InvalidPathException(path, false);
			}
			throw new InvalidPathException(path, true);
		}
		if(!typeCheck.get()) {
			yaml.set(path, yaml.getDefaults().get(path));
			throw new IllegalFormatException(type, yaml.getString(path));
		}
	}
	
}
