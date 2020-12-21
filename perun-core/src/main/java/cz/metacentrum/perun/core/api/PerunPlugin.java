package cz.metacentrum.perun.core.api;

import org.pf4j.ExtensionPoint;

/**
 * @author Daniel Valanský
 */
public interface PerunPlugin extends ExtensionPoint {

	void start(Perun perun);

	void stop();

}
