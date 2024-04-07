package ccetl.flashlight.listeners;

import ccetl.flashlight.Cancelable;
import ccetl.flashlight.DefaultPriorities;
import ccetl.flashlight.annotations.Nullable;

import java.util.function.Predicate;

public class Canceler<E extends Cancelable> extends LambdaListener<E> {
    public Canceler(Class<? super E> target, byte priority, @Nullable Predicate<E> predicate) {
        super(target, predicate, priority, Cancelable::cancel);
    }

    public Canceler(Class<? super E> target, Predicate<E> predicate) {
        this(target, DefaultPriorities.NORMAL, predicate);
    }

    public Canceler(Class<? super E> target, byte priority) {
        this(target, priority, null);
    }

    public Canceler(Class<? super E> target) {
        this(target, DefaultPriorities.NORMAL, null);
    }

    @Override
    public void invoke(E event) {
        if (event.isCanceled()) {
            return;
        }

        super.invoke(event);
    }
}