package net.numaclient.numa;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Numa implements ModInitializer {
	public static final Logger logger = LogManager.getLogger();

	public static Numa INSTANCE;

	public Numa() {
		INSTANCE = this;
	}

	public Dupe dupe = new Dupe();

	@Override
	public void onInitialize() {
		logger.info("Numa Dupe v2 has been initialized!");
	}
}
