package io.sarl.demos.fireworks;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.List;
import java.util.Vector;
import javafx.scene.paint.Color;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class FirePos {
  public final int FIREREFRESHDELAY = 100;
  
  private List<Vector<Double>> positions;
  
  private Color color;
  
  @Pure
  public List<Vector<Double>> getPositions() {
    return this.positions;
  }
  
  public List<Vector<Double>> setPositions(final List<Vector<Double>> positions) {
    return this.positions = positions;
  }
  
  @Pure
  public Color getColor() {
    return this.color;
  }
  
  public Color setColor(final Color color) {
    return this.color = color;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    FirePos other = (FirePos) obj;
    if (other.FIREREFRESHDELAY != this.FIREREFRESHDELAY)
      return false;
    if (this.positions == null) {
      if (other.positions != null)
        return false;
    } else if (!this.positions.equals(other.positions))
      return false;
    if (this.color == null) {
      if (other.color != null)
        return false;
    } else if (!this.color.equals(other.color))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + this.FIREREFRESHDELAY;
    result = prime * result + ((this.positions== null) ? 0 : this.positions.hashCode());
    result = prime * result + ((this.color== null) ? 0 : this.color.hashCode());
    return result;
  }
  
  @SyntheticMember
  public FirePos() {
    super();
  }
}
