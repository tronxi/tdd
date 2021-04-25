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
  public void givenIntervalOpenOpenWhenIncludeLeftPointFromIntervalOpenOpenThenIntersect() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    Interval interval1 = new IntervalBuilder().open(left.getLess()).open(left.getGreater()).build();
    assertTrue(interval.intersect(interval1));
  }

  @Test
  public void givenIntervalOpenOpenWhenIncludeRightPointFromIntervalOpenOpenThenIntersect() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    Interval interval1 = new IntervalBuilder().open(right.getLess()).open(right.getGreater()).build();
    assertTrue(interval.intersect(interval1));
  }

  @Test
  public void givenIntervalOpenOpenWhenIncludeLeftAndRightPointFromIntervalOpenOpenThenIntersect() {
    Interval interval = this.intervalBuilder.open(left.getGreater()).open(right.getLess()).build();
    Interval interval1 = new IntervalBuilder().open(left.getEquals()).open(right.getEquals()).build();
    assertTrue(interval.intersect(interval1));
  }

  @Test
  public void givenIntervalOpenOpenWhenIntersectWithTheSameThenNotIntersect() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.intersect(interval));
  }

  @Test
  public void givenIntervalClosedClosedWhenIntersectWithTheSameThenIntersect() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertTrue(interval.intersect(interval));
  }

  @Test
  public void givenIntervalClosedClosedWhenIncludeLeftPointFromIntervalClosedClosedThenIntersect() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval interval1 = new IntervalBuilder().closed(left.getLess()).closed(left.getGreater()).build();
    assertTrue(interval.intersect(interval1));
  }

  @Test
  public void givenIntervalClosedClosedWhenIncludeRightPointFromIntervalClosedClosedThenIntersect() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval interval1 = new IntervalBuilder().closed(right.getLess()).closed(right.getGreater()).build();
    assertTrue(interval.intersect(interval1));
  }

  @Test
  public void givenIntervalClosedClosedWhenIncludeLeftAndRightPointFromIntervalClosedClosedThenIntersect() {
    Interval interval = this.intervalBuilder.closed(left.getGreater()).closed(right.getLess()).build();
    Interval interval1 = new IntervalBuilder().closed(left.getEquals()).closed(right.getEquals()).build();
    assertTrue(interval.intersect(interval1));
  }

  @Test
  public void givenIntervalClosedClosedWhenIncludeLeftPointFromIntervalOpenOpenThenNotIntersect() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval interval1 = new IntervalBuilder().open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.intersect(interval1));
  }


}