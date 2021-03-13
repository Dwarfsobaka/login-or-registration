package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/*Анимация*/

public class Shake {
    private TranslateTransition tt;

    public Shake (Node node){       //конструктор с любым объектом на нашем окне
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0f);        //отступ от X
        tt.setByX(10f);         //передвинется на 10f
        tt.setCycleCount(3);    //сколько раз проиграет анимация
        tt.setAutoReverse(true);        //чтобы анимированный объект возвращался обратно
    }
    public  void playAnim (){
        tt.playFromStart();     //проигрываем анимацию
    }
}
