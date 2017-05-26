package io.sarl.demos.fireworks;

import io.janusproject.Boot;
import io.janusproject.kernel.Kernel;
import io.sarl.demos.fireworks.agents.LaunchingArea;
import io.sarl.demos.fireworks.gui.FXMLViewerController;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SarlSpecification("0.5")
@SarlElementType(8)
@SuppressWarnings("all")
public class Firework {
  public static Kernel main(final FXMLViewerController controller) {
    try {
      Kernel _xblockexpression = null;
      {
        Boot.setOffline(true);
        Boot.setVerboseLevel(7);
        _xblockexpression = Boot.startJanus(LaunchingArea.class, controller);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @SyntheticMember
  public Firework() {
    super();
  }
}
