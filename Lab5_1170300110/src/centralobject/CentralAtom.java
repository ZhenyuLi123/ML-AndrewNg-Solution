package centralobject;

public class CentralAtom {
  private String elementName;

  // RI:
  // �Ϸ���ElementName
  // AF:
  // ���ϵͳ�е����������� for app 3
  // ����Ԫ��������
  // ��ֹREP��¶:
  // ʹ��private����
  // ������set����

  public CentralAtom(String str) {
    this.elementName = str;
  }

  public String getElementName() {
    return elementName;
  }
}
