package cz.metacentrum.perun.core.impl;

import cz.metacentrum.perun.core.api.Perun;
import cz.metacentrum.perun.core.api.PerunPlugin;
import cz.metacentrum.perun.core.blImpl.PerunBlImpl;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

/**
 * Adapter class for PF4J plugin manager.
 * Provides default methods for working with plugins.
 *
 * @author Daniel Valanský
 */
public class PerunPluginManagerImpl {

	private final PerunBlImpl perun;
	private PluginManager pluginManager;

	private final static Logger log = LoggerFactory.getLogger(PerunPluginManagerImpl.class);


	PerunPluginManagerImpl(PerunBlImpl perun) {
		this.perun = perun;
	}

	/**
	 * Initializes plugin manager.
	 *
	 * @param pluginPath path to plugins
	 */

	public void init(Path pluginPath) {

		pluginManager = new DefaultPluginManager(pluginPath);
	}

	public void startPlugins() {

		pluginManager.loadPlugins();
		pluginManager.startPlugins();

		final List<PerunPlugin> perunPlugins = pluginManager.getExtensions(PerunPlugin.class);

		log.debug("Starting plugins.");
		log.debug("{} Plugin(s) found ", perunPlugins.size());

		for (PerunPlugin plugin : perunPlugins) {
			plugin.start(perun);
		}

	}

	public void stopPlugins() {
		final List<PerunPlugin> perunPlugins = pluginManager.getExtensions(PerunPlugin.class);
		log.debug("Stopping plugins.");

		for (PerunPlugin plugin : perunPlugins) {
			plugin.stop();
		}
	}

}
