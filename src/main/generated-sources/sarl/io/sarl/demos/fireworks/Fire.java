package io.sarl.demos.fireworks;

import io.sarl.core.AgentTask;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.ExternalContextAccess;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.core.Schedules;
import io.sarl.core.Time;
import io.sarl.demos.fireworks.Exit;
import io.sarl.demos.fireworks.FireReady;
import io.sarl.demos.fireworks.Freeze;
import io.sarl.demos.fireworks.Positions;
import io.sarl.demos.fireworks.UpdateFirePosition;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import io.sarl.util.Scopes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.function.Consumer;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.DoubleExtensions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class Fire extends Agent {
  private List<Double> x;
  
  private List<Double> y;
  
  private int lifetime = 300;
  
  private boolean frozen = false;
  
  private boolean destroyed = false;
  
  private boolean exited = false;
  
  private Positions grid;
  
  private Double xf;
  
  private Double yf;
  
  private UUID id = UUID.randomUUID();
  
  private UUID parentID;
  
  private AgentTask move;
  
  private UUID parentAgent;
  
  @SyntheticMember
  private void $behaviorUnit$Freeze$0(final Freeze occurrence) {
    this.frozen = occurrence.value;
    if (this.frozen) {
      Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER = this.$castSkill(Schedules.class, (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES == null || this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES = $getSkill(Schedules.class)) : this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES);
      _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER.cancel(this.move, true);
    } else {
      Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
      UpdateFirePosition _updateFirePosition = new UpdateFirePosition();
      _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.wake(_updateFirePosition);
    }
  }
  
  @SyntheticMember
  private void $behaviorUnit$Initialize$1(final Initialize occurrence) {
    ArrayList<Double> _arrayList = new ArrayList<Double>();
    this.x = _arrayList;
    ArrayList<Double> _arrayList_1 = new ArrayList<Double>();
    this.y = _arrayList_1;
    boolean _equals = Integer.valueOf(((List<Object>)Conversions.doWrapArray(occurrence.parameters)).size()).equals(Integer.valueOf(4));
    if (_equals) {
      Object _get = occurrence.parameters[0];
      this.x.add(((Double) _get));
      Object _get_1 = occurrence.parameters[1];
      this.y.add(((Double) _get_1));
      Object _get_2 = occurrence.parameters[2];
      this.grid = ((Positions) _get_2);
      Object _get_3 = occurrence.parameters[3];
      this.parentID = ((UUID) _get_3);
    } else {
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Error in fire Initialize : bad parameter number");
    }
    this.parentAgent = occurrence.spawner;
    double _random = Math.random();
    double _multiply = (_random * 10.0);
    double _random_1 = Math.random();
    double _multiply_1 = (_random_1 * 5);
    double _minus = (_multiply - _multiply_1);
    this.xf = Double.valueOf(_minus);
    double _random_2 = Math.random();
    double _multiply_2 = (_random_2 * 10.0);
    double _plus = (_multiply_2 + 1.0);
    this.yf = Double.valueOf(_plus);
  }
  
  @SyntheticMember
  private void $behaviorUnit$FireReady$2(final FireReady occurrence) {
    Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER = this.$castSkill(Schedules.class, (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES == null || this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES = $getSkill(Schedules.class)) : this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES);
    final Procedure1<Agent> _function = (Agent it) -> {
      try {
        Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
        UpdateFirePosition _updateFirePosition = new UpdateFirePosition();
        _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.wake(_updateFirePosition);
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          e.printStackTrace();
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    };
    this.move = _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER.atFixedDelay(30, _function);
  }
  
  @SyntheticMember
  private void $behaviorUnit$UpdateFirePosition$3(final UpdateFirePosition occurrence) {
    Double _last = IterableExtensions.<Double>last(this.x);
    double newx = DoubleExtensions.operator_plus(_last, this.xf);
    Double _last_1 = IterableExtensions.<Double>last(this.y);
    double newy = DoubleExtensions.operator_plus(_last_1, this.yf);
    this.x.add(Double.valueOf(newx));
    this.y.add(Double.valueOf(newy));
    if ((this.grid != null)) {
      ArrayList<Vector<Double>> list = new ArrayList<Vector<Double>>();
      final Consumer<Double> _function = (Double pos) -> {
        Vector<Double> nvect = new Vector<Double>(2);
        nvect.add(pos);
        list.add(nvect);
      };
      this.x.forEach(_function);
      final Procedure2<Double, Integer> _function_1 = (Double pos, Integer i) -> {
        Vector<Double> nvect = list.get((i).intValue());
        nvect.add(pos);
        list.set((i).intValue(), nvect);
      };
      IterableExtensions.<Double>forEach(this.y, _function_1);
      this.grid.setFirePosition(this.id, this.parentID, list);
    }
    this.lifetime = (this.lifetime - 10);
    if ((this.lifetime <= 0)) {
      this.grid.removeFirePosition(this.id);
      this.cleanBeforeExit();
      Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER = this.$castSkill(Schedules.class, (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES == null || this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES = $getSkill(Schedules.class)) : this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES);
      final Procedure1<Agent> _function_2 = (Agent it) -> {
        Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
        _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
      };
      _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER.in(1000, _function_2);
    }
  }
  
  @SyntheticMember
  @Pure
  private boolean $behaviorUnitGuard$UpdateFirePosition$3(final UpdateFirePosition it, final UpdateFirePosition occurrence) {
    return ((this.isFromMe(occurrence) && (!this.frozen)) && (!this.destroyed));
  }
  
  protected boolean cleanBeforeExit() {
    boolean _xblockexpression = false;
    {
      Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER = this.$castSkill(Schedules.class, (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES == null || this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES = $getSkill(Schedules.class)) : this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES);
      _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER.cancel(this.move, true);
      this.exited = true;
      _xblockexpression = this.destroyed = true;
    }
    return _xblockexpression;
  }
  
  @SyntheticMember
  private void $behaviorUnit$Exit$4(final Exit occurrence) {
    this.frozen = true;
    this.cleanBeforeExit();
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    Exit _exit = new Exit();
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_exit, Scopes.addresses(_$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.getDefaultSpace().getAddress(this.parentAgent)));
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  @SyntheticMember
  @Pure
  private boolean $behaviorUnitGuard$Exit$4(final Exit it, final Exit occurrence) {
    return ((!this.exited) && it.isFrom(this.getParentID()));
  }
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE)", imported = Lifecycle.class)
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING)", imported = Logging.class)
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @Extension
  @ImportedCapacityFeature(Behaviors.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS)", imported = Behaviors.class)
  private Behaviors $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class);
    }
    return $castSkill(Behaviors.class, this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS)", imported = DefaultContextInteractions.class)
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(ExternalContextAccess.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(ExternalContextAccess.class, (this.$CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS == null || this.$CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS = $getSkill(ExternalContextAccess.class)) : this.$CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS)", imported = ExternalContextAccess.class)
  private ExternalContextAccess $CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS == null || this.$CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS = $getSkill(ExternalContextAccess.class);
    }
    return $castSkill(ExternalContextAccess.class, this.$CAPACITY_USE$IO_SARL_CORE_EXTERNALCONTEXTACCESS);
  }
  
  @Extension
  @ImportedCapacityFeature(Schedules.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_SCHEDULES;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Schedules.class, (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES == null || this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES = $getSkill(Schedules.class)) : this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES)", imported = Schedules.class)
  private Schedules $CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES == null || this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES = $getSkill(Schedules.class);
    }
    return $castSkill(Schedules.class, this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES);
  }
  
  @Extension
  @ImportedCapacityFeature(Time.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_TIME;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Time.class, (this.$CAPACITY_USE$IO_SARL_CORE_TIME == null || this.$CAPACITY_USE$IO_SARL_CORE_TIME.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_TIME = $getSkill(Time.class)) : this.$CAPACITY_USE$IO_SARL_CORE_TIME)", imported = Time.class)
  private Time $CAPACITY_USE$IO_SARL_CORE_TIME$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_TIME == null || this.$CAPACITY_USE$IO_SARL_CORE_TIME.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_TIME = $getSkill(Time.class);
    }
    return $castSkill(Time.class, this.$CAPACITY_USE$IO_SARL_CORE_TIME);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$FireReady(final FireReady occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$FireReady$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Freeze(final Freeze occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Freeze$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Exit(final Exit occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    if ($behaviorUnitGuard$Exit$4(occurrence, occurrence)) {
      ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Exit$4(occurrence));
    }
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$UpdateFirePosition(final UpdateFirePosition occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    if ($behaviorUnitGuard$UpdateFirePosition$3(occurrence, occurrence)) {
      ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$UpdateFirePosition$3(occurrence));
    }
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
    Fire other = (Fire) obj;
    if (this.x == null) {
      if (other.x != null)
        return false;
    } else if (!this.x.equals(other.x))
      return false;
    if (this.y == null) {
      if (other.y != null)
        return false;
    } else if (!this.y.equals(other.y))
      return false;
    if (other.lifetime != this.lifetime)
      return false;
    if (other.frozen != this.frozen)
      return false;
    if (other.destroyed != this.destroyed)
      return false;
    if (other.exited != this.exited)
      return false;
    if (this.grid == null) {
      if (other.grid != null)
        return false;
    } else if (!this.grid.equals(other.grid))
      return false;
    if (this.xf == null) {
      if (other.xf != null)
        return false;
    } else if (!this.xf.equals(other.xf))
      return false;
    if (this.yf == null) {
      if (other.yf != null)
        return false;
    } else if (!this.yf.equals(other.yf))
      return false;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (this.parentID == null) {
      if (other.parentID != null)
        return false;
    } else if (!this.parentID.equals(other.parentID))
      return false;
    if (this.move == null) {
      if (other.move != null)
        return false;
    } else if (!this.move.equals(other.move))
      return false;
    if (this.parentAgent == null) {
      if (other.parentAgent != null)
        return false;
    } else if (!this.parentAgent.equals(other.parentAgent))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.x== null) ? 0 : this.x.hashCode());
    result = prime * result + ((this.y== null) ? 0 : this.y.hashCode());
    result = prime * result + this.lifetime;
    result = prime * result + (this.frozen ? 1231 : 1237);
    result = prime * result + (this.destroyed ? 1231 : 1237);
    result = prime * result + (this.exited ? 1231 : 1237);
    result = prime * result + ((this.grid== null) ? 0 : this.grid.hashCode());
    result = prime * result + ((this.xf== null) ? 0 : this.xf.hashCode());
    result = prime * result + ((this.yf== null) ? 0 : this.yf.hashCode());
    result = prime * result + ((this.id== null) ? 0 : this.id.hashCode());
    result = prime * result + ((this.parentID== null) ? 0 : this.parentID.hashCode());
    result = prime * result + ((this.move== null) ? 0 : this.move.hashCode());
    result = prime * result + ((this.parentAgent== null) ? 0 : this.parentAgent.hashCode());
    return result;
  }
  
  @SyntheticMember
  public Fire(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Fire(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
}
