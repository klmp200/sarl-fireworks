package io.sarl.demos.fireworks;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class Explode extends Event {
  @SyntheticMember
  public Explode() {
    super();
  }
  
  @SyntheticMember
  public Explode(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}