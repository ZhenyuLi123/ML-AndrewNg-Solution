package circularorbits;

public interface MyIterator<L, E> {
  /** hasNext.
   * 检测是否有下一个元素
   *
   * @return 如果有，返回true，否则返回false
   */
  boolean hasNext();

  /** next.
   * 返回当前元素，并将指针指向下一个元素
   *
   * @return 当前元素
   */
  E next();

}
