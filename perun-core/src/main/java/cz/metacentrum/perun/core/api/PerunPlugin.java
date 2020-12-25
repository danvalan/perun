package cz.metacentrum.perun.core.api;

import cz.metacentrum.perun.core.blImpl.PerunBlImpl;
import org.pf4j.ExtensionPoint;

/**
 * @author Daniel Valanský
 */
public interface PerunPlugin extends ExtensionPoint {

	void start(PerunBlImpl perun);

	void stop();

}
