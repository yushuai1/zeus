package pro;

public class Test {

    static class BytesTest{
        private int yu;
        private String name;

        public int getYu() {
            return yu;
        }

        public void setYu(int yu) {
            this.yu = yu;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception{
        MytOuterClass.Myt.Builder myt = MytOuterClass.Myt.newBuilder();
        myt.setAge(100);
        myt.setName("nihao");
        MytOuterClass.Myt bytes = myt.build();
        byte[] data = bytes.toByteArray();


        Tcp tcp = new Tcp();
        byte[] result = tcp.sentTcp(data);
//        System.out.println(data.length);

        MytOuterClass.Myt mm = MytOuterClass.Myt.parseFrom(result);
        System.out.println(mm.getAge()+"-------"+mm.getName());


    }
}
