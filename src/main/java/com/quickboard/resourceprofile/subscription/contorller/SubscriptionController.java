package com.quickboard.resourceprofile.subscription.contorller;

import com.quickboard.resourceprofile.common.security.dto.Passport;
import com.quickboard.resourceprofile.subscription.dto.SubscriptionRequest;
import com.quickboard.resourceprofile.subscription.dto.SubscriptionResponse;
import com.quickboard.resourceprofile.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rsc/v1")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/profiles/{id}/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    public Page<SubscriptionResponse> getSubscriptions(@PathVariable("id") Long profileId,
                                                       @ParameterObject Pageable pageable){
        return subscriptionService.searchSubscriptions(profileId, pageable);
    }

    @PostMapping("/profiles/me/subscriptions")
    public void postSubscription(Passport passport,
                                 @RequestBody SubscriptionRequest subscriptionRequest){
        subscriptionService.createSubscription(passport.endUserDetails().profileId(), subscriptionRequest);
    }

    @DeleteMapping("/profiles/me/subscriptions/{subscription-id}")
    public void deleteSubscription(@PathVariable("subscription-id") Long subscriptionId){
        subscriptionService.deleteSubscription(subscriptionId);
    }
}
