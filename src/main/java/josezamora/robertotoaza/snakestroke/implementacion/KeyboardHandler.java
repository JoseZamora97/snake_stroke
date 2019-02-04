package josezamora.robertotoaza.snakestroke.implementacion;


import android.view.View;

import java.util.ArrayList;
import java.util.List;

import josezamora.robertotoaza.snakestroke.interfaces.Input.KeyEvent;


public class KeyboardHandler implements View.OnKeyListener {

    boolean[] pressdKeys = new boolean[128];
    Pool<KeyEvent> keyEventPool;
    List<KeyEvent> keyEventsBuffer = new ArrayList<>();
    List<KeyEvent> keyEvents = new ArrayList<>();

    public KeyboardHandler(View view){
        Pool.PoolObjectFactory<KeyEvent> factory = new Pool.PoolObjectFactory<KeyEvent>(){
            @Override
            public KeyEvent createObject() {
                return new KeyEvent();
            }
        };
    }

    public boolean isKeyPressed(int keyCode){
        if(keyCode < 0 || keyCode > 127)
            return false;
        return pressdKeys[keyCode];
    }

    public List<KeyEvent> getKeyEvents() {
        synchronized (this) {
            int len = keyEvents.size();
            for(int i =0; i<len; i++)
                keyEventPool.free(keyEvents.get(i));
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();

            return keyEvents;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
        if(event.getAction()== android.view.KeyEvent.ACTION_MULTIPLE)
            return false;
        synchronized (this) {
            KeyEvent keyEvent = keyEventPool.newObject();
            KeyEvent.keyCode = keyCode;
            KeyEvent.keyChar = (char)event.getUnicodeChar();

            if(event.getAction() == android.view.KeyEvent.ACTION_UP){
                keyEvent.type = KeyEvent.KEY_UP;
                if(keyCode > 0 && keyCode < 127)
                    pressdKeys[keyCode] = false;
            }

            keyEventsBuffer.add(keyEvent);
        }

        return false;
    }
}
