package P3;


import static org.junit.Assert.*;



import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class PositionTest {
    
	// Testing strategy
    // TODO ����һ��Player���͵Ķ���������з������ν��в���
    @Test
    public void test() {
    	Position p = new Position(1, 1);
    	Position p1 = new Position(1, 1);
    	
    	assertEquals("expected equal", true, p.equals(p1));
    	assertEquals("expected equal", true, p.equals(p1));
    	
    	assertEquals("expected equal", 1, p.x);
    	assertEquals("expected equal", 1, p.y);
    }
}