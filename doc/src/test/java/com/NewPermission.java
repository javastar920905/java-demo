package com;

/**
 * @author ouzhx on 2018/5/9.
 */
public class NewPermission {
  // 是否允许查询，二进制第1位，0表示否，1表示是
  public static final int ALLOW_SELECT = 1 << 0; // 0001

  // 是否允许新增，二进制第2位，0表示否，1表示是
  public static final int ALLOW_INSERT = 1 << 1; // 0010

  // 是否允许修改，二进制第3位，0表示否，1表示是
  public static final int ALLOW_UPDATE = 1 << 2; // 0100

  // 是否允许删除，二进制第4位，0表示否，1表示是
  public static final int ALLOW_DELETE = 1 << 3; // 1000

  // 存储目前的权限状态
  private int flag;

  /**
   * 重新设置权限
   */
  public void setPermission(int permission) {
    flag = permission;
  }

  /**
   * 添加一项或多项权限
   */
  public void enable(int permission) {
    flag |= permission;
  }

  /**
   * 删除一项或多项权限
   */
  public void disable(int permission) {
    flag &= ~permission;
  }

  /**
   * 是否拥某些权限
   */
  public boolean isAllow(int permission) {
    return (flag & permission) == permission;
  }

  /**
   * 是否禁用了某些权限
   */
  public boolean isNotAllow(int permission) {
    return (flag & permission) == 0;
  }

  /**
   * 是否仅仅拥有某些权限
   */
  public boolean isOnlyAllow(int permission) {
    return flag == permission;
  }


    /**
     *
     * 以上代码中，用四个常量表示了每个二进制位代码的权限项。
     * 例如：
     ALLOW_SELECT = 1 << 0 转成二进制就是0001，二进制第一位表示Select权限。
     ALLOW_INSERT = 1 << 1 转成二进制就是0010，二进制第二位表示Insert权限。

     private int flag存储了各种权限的启用和停用状态，相当于代替了Permission中的四个boolean类型的变量。

     用flag的四个二进制位来表示四种权限的状态，每一位的0和1代表一项权限的启用和停用，下面列举了部分状态表示的权限：

     flag	    删除	修改	新增	查询
     1(0001)	0	  0	  0	  1	      只允许查询（即等于ALLOW_SELECT）
     2(0010)	0	  0	  1	  0	      只允许新增（即等于ALLOW_INSERT）
     4(0100)	0	  1	  0	  0     	只允许修改（即等于ALLOW_UPDATE）
     8(1000)	1	  0	  0	  0	      只允许删除（即等于ALLOW_DELETE）
     3(0011)	0	  0	  1	  1	      只允许查询和新增
     0	      0	  0	  0	  0	      四项权限都不允许
     15(1111)	1	  1	  1	  1	      四项权限都允许

     使用位掩码的方式，只需要用一个大于或等于0且小于16的整数即可表示所有的16种权限的状态。

     一些用到位掩码的源代码:java.lang.reflect.Modifier
     * @param args
     */
  public static void main(String[] args) {
    // 参考文档 http://xxgblog.com/2013/09/15/java-bitmask/

    NewPermission permission = new NewPermission();
    System.out.println(Integer.toBinaryString(permission.flag));// 输出 0
    // 添加一项update 权限
    permission.enable(ALLOW_UPDATE);
    System.out.println(Integer.toBinaryString(permission.flag));// 输出 0100

    // 添加Insert、Update、Delete三项权限
    permission.enable(
        NewPermission.ALLOW_INSERT | NewPermission.ALLOW_UPDATE | NewPermission.ALLOW_DELETE);
    System.out.println(Integer.toBinaryString(permission.flag));// 输出 1110


    // 判断是否禁用Select权限(0001)
    System.out.println(permission.isNotAllow(NewPermission.ALLOW_SELECT));// true
    // 判断是否允许Update权限(0100)
    System.out.println(permission.isAllow(NewPermission.ALLOW_UPDATE));// true
    // 判断是否允许Select和Insert、Update权限(0111)
    System.out.println(permission.isAllow(
        NewPermission.ALLOW_SELECT | NewPermission.ALLOW_INSERT | NewPermission.ALLOW_UPDATE));// 输出false
    // 判断是只否允许Select和Insert权限(0011)
    System.out
        .println(permission.isOnlyAllow(NewPermission.ALLOW_SELECT | NewPermission.ALLOW_INSERT));// 输出false

  }
}
