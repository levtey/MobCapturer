package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Slime;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class SlimeAdapter<T extends Slime> implements MobAdapter<T> {
	
	private final Class<T> entityClass;
	
	public SlimeAdapter(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public List<String> getLore(JsonObject json) {
		List<String> lore = MobAdapter.super.getLore(json);

		lore.add(ChatColor.GRAY + "Size: " + ChatColor.RESET + json.get("size").getAsInt());
		
		return lore;
	}
	
	@Override
	public void apply(T entity, JsonObject json) {
		MobAdapter.super.apply(entity, json);
		
		entity.setSize(json.get("size").getAsInt());
	}
	
	@Override
	public JsonObject save(T entity) {
		JsonObject json = MobAdapter.super.save(entity);
		
		json.addProperty("size", entity.getSize());
		
		return json;
	}

	@Override
	public Class<T> getEntityClass() {
		return entityClass;
	}

}