package com.dalhousie.foodnculture.apifacade;

public class ApiFacade {

    private static ApiFacade apiFacade;
    private final IRequest request;
    private final IUserOperation userOperation;
    private final IAmenityOperation amenityOperation;
    private final IAuthenticationOperation authenticationOperation;
    private final IDonationOperation donationOperation;
    private final IEventMemberOperation eventMemberOperation;
    private final IEventOperation eventOperation;
    private final IFeedbackOperation feedbackOperation;
    private final IVenueOperation venueOperation;

    private ApiFacade(IRequest request) {
        this.request = request;
        this.userOperation = new UsersApi(this.request);
        this.amenityOperation = new AmenitiesApi(this.request);
        this.authenticationOperation = new AuthenticationApi(this.request);
        this.donationOperation = new DonationApi(this.request);
        this.eventMemberOperation = new EventMemberApi(this.request);
        this.eventOperation = new EventApi(this.request);
        this.feedbackOperation = new FeedbackApi(this.request);
        this.venueOperation = new VenuesApi(this.request);
    }

    public static ApiFacade getInstance() {
        if (apiFacade == null) {
            apiFacade = new ApiFacade(HTTPRequest.getInstance());
        }
        return apiFacade;
    }

    public IUserOperation getUserApi() {
        return this.userOperation;
    }

    public IAmenityOperation getAmenitiesApi() {
        return this.amenityOperation;
    }

    public IAuthenticationOperation getAuthenticationApi() {
        return this.authenticationOperation;
    }

    public IDonationOperation getDonationApi() {
        return this.donationOperation;
    }

    public IEventMemberOperation getEventMemberApi() {
        return this.eventMemberOperation;
    }

    public IEventOperation getEventApi() {
        return this.eventOperation;
    }

    public IFeedbackOperation getFeedbackApi() {
        return this.feedbackOperation;
    }

    public IVenueOperation getVenueApi() {
        return this.venueOperation;
    }
}
