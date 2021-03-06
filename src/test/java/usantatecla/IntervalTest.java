package usantatecla;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntervalTest {
  
  private Point left = new Point(-2.2);
  private Point right = new Point(4.4);
  private IntervalBuilder intervalBuilder;

  @BeforeEach
  public void before(){
    this.left = new Point(-2.2);
    this.right = new Point(4.4);
    this.intervalBuilder = new IntervalBuilder();
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenInc3ludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWit3hIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithInclude5dValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenTwoIntervalIntersectionWhenBothAreTheSameThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();

    assertTrue(interval.intersection(interval));
  }

  @Test
  public void givenTwoIntervalWhenNotExistsIntersectionThenTrue() {
    Interval firstInterval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    this.intervalBuilder = new IntervalBuilder();
    Interval secondInterval = this.intervalBuilder.closed(new Point(5.0).getEquals()).closed(new Point(10.0).getEquals()).build();

    assertFalse(firstInterval.intersection(secondInterval));
  }

  @Test
  public void givenTwoIntervalsWhenInterval1IntersectionInterval2WithMax2IncludedThenTrue() {
    Interval firstInterval = this.intervalBuilder.closed(new Point(-2.0).getEquals()).closed(new Point(2.0).getEquals()).build();
    this.intervalBuilder = new IntervalBuilder();
    Interval secondInterval = this.intervalBuilder.closed(new Point(-5.0).getEquals()).closed(new Point(1.0).getEquals()).build();

    assertTrue(firstInterval.intersection(secondInterval));
  }

  @Test
  public void givenTwoIntervalsWhenInterval1IntersectionInterval2WithMin2IncludedThenTrue() {
    Interval firstInterval = this.intervalBuilder.closed(new Point(-2.0).getEquals()).closed(new Point(2.0).getEquals()).build();
    this.intervalBuilder = new IntervalBuilder();
    Interval secondInterval = this.intervalBuilder.closed(new Point(1.0).getEquals()).closed(new Point(5.0).getEquals()).build();

    assertTrue(firstInterval.intersection(secondInterval));
  }

  @Test
  public void givenTwoIntervalsWhenInterval2IncludeInterval1ThenTrue() {
    Interval firstInterval = this.intervalBuilder.closed(new Point(-2.0).getEquals()).closed(new Point(2.0).getEquals()).build();
    this.intervalBuilder = new IntervalBuilder();
    Interval secondInterval = this.intervalBuilder.closed(new Point(-4.0).getEquals()).closed(new Point(4.0).getEquals()).build();

    assertTrue(firstInterval.intersection(secondInterval));
  }

}
