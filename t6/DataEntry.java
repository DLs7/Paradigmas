import javafx.beans.property.SimpleStringProperty;

public class DataEntry {
    private SimpleStringProperty c1;
    private SimpleStringProperty c2;
    private SimpleStringProperty c3;
    private SimpleStringProperty c4;
    private SimpleStringProperty c5;
    private SimpleStringProperty c6;
    private SimpleStringProperty c7;
    private SimpleStringProperty c8;
    private SimpleStringProperty c9;
    private SimpleStringProperty c10;
    private SimpleStringProperty c11;
    
    public DataEntry(String d1, String d2, String d3, String d4,
    String d5, String d6, String d7, String d8, String d9,
    String d10, String d11) {
      this.c1 = new SimpleStringProperty(d1);
      this.c2 = new SimpleStringProperty(d2);
      this.c3 = new SimpleStringProperty(d3);
      this.c4 = new SimpleStringProperty(d4);
      this.c5 = new SimpleStringProperty(d5);
      this.c6 = new SimpleStringProperty(d6);
      this.c7 = new SimpleStringProperty(d7);
      this.c8 = new SimpleStringProperty(d8);
      this.c9 = new SimpleStringProperty(d9);
      this.c10 = new SimpleStringProperty(d10);
      this.c11 = new SimpleStringProperty(d11);
    }

    public SimpleStringProperty property1() {
      return c1;
    }
    public String get1() {
      return c1.get();
    }
    public void set1(String d) {
       c1.set(d);
    }

    public SimpleStringProperty property2() {
        return c2;
      }
      public String get2() {
        return c2.get();
      }
      public void set2(String d) {
         c2.set(d);
    }

    public SimpleStringProperty property3() {
        return c3;
      }
      public String get3() {
        return c3.get();
      }
      public void set3(String d) {
         c3.set(d);
    }

    public SimpleStringProperty property4() {
        return c4;
      }
      public String get4() {
        return c4.get();
      }
      public void set4(String d) {
         c4.set(d);
    }

    public SimpleStringProperty property5() {
        return c5;
      }
      public String get5() {
        return c5.get();
      }
      public void set5(String d) {
         c5.set(d);
    }

    public SimpleStringProperty property6() {
        return c6;
      }
      public String get6() {
        return c6.get();
      }
      public void set6(String d) {
         c6.set(d);
    }

    public SimpleStringProperty property7() {
        return c7;
      }
      public String get7() {
        return c7.get();
      }
      public void set7(String d) {
         c7.set(d);
    }

    public SimpleStringProperty property8() {
        return c8;
      }
      public String get8() {
        return c8.get();
      }
      public void set8(String d) {
         c8.set(d);
    }

    public SimpleStringProperty property9() {
        return c9;
      }
      public String get9() {
        return c9.get();
      }
      public void set9(String d) {
         c9.set(d);
    }

    public SimpleStringProperty property10() {
        return c10;
      }
      public String get10() {
        return c10.get();
      }
      public void set10(String d) {
         c10.set(d);
    }

    public SimpleStringProperty property11() {
        return c11;
      }
      public String get11() {
        return c11.get();
      }
      public void set11(String d) {
         c11.set(d);
    }
}