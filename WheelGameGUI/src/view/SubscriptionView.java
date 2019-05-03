package view;

import java.util.concurrent.Flow;

/**
 * Abstract class used to enable pub sub flow on views.
 * Since some view may not need this feature.
 */
public abstract class SubscriptionView implements View, Flow.Subscriber {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    protected Flow.Subscription getSubscription() {
        return this.subscription;
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
