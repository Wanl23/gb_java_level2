package lesson5;

public class Main {
    int size = 10000000;
    float[] list = new float[size];
    int threads = 4;
    int subArrLength = list.length / threads;
    long time;
    float[] tempArr;

    public void fillArr(){
        for (int i = 0; i < list.length; i++) {
            list[i] = 1;
        }
        time = System.currentTimeMillis();
        for (int i = 0; i < list.length; i++) {
            list[i] = (float)(list[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Filling array with one thread lasted: " + time);
    }

    public void fillArrByThreads(){
        for (int i = 0; i < list.length; i++) {
            list[i] = 1;
        }
        time = System.currentTimeMillis();
        Thread thread = new Thread();
        for (int i = 0; i < threads; i++) {
            subArrLength = (subArrLength * i + 1 < size)?  subArrLength:subArrLength + (size - subArrLength * i);
            tempArr = new float[subArrLength];
            System.arraycopy(list, i * subArrLength, tempArr, 0, subArrLength);

            MathFunc m = new MathFunc(tempArr);
            m.start();
            System.arraycopy(tempArr, 0, list, i * subArrLength, subArrLength);
            thread = m;
        }
        while(thread.isAlive()){}
        time = System.currentTimeMillis() - time;
        System.out.println("Filling array with " + threads + " threads lasted: " + time);

    }


    public static void main(String[] args) {
        Main main = new Main();
        main.fillArr();
        main.fillArrByThreads();
    }
}

class MathFunc extends Thread{
    float[] array;

    MathFunc(float[] array){
        this.array = array;
    }

    @Override
    public void run(){
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}



