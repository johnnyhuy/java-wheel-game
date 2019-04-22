package view;

import java.util.concurrent.Flow;

/**
 * Abstract class used to enable pub sub flow on views.
 * Since some view may not need this feature.
 */
public abstract class SubscriptionView implements View, Flow.Subscriber<Integer> {
    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
