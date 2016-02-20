define([
    'jquery',
    'core/BaseView',
    'models/UserTokenModel',
    'text!templates/tplTokenValidate.html'
], function($, BaseView, UserTokenModel, tplTokenValidate){

    var TokenValidateView = BaseView.extend({
        template: _.template(tplTokenValidate),

        events: {
        },

        initialize: function(opts) {;
            this.model = new UserTokenModel();
            this.listenTo(this.model, 'sync', this.syncUserToken);
            this.listenTo(this.model, 'error', this.errorUserToken);

            this.model.set({id: opts.token});
            this.model.fetch();
        },

        render: function() {
            return this;
        },

        syncUserToken: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        errorUserToken: function(model, response) {
            alert(error);
        }
    });

    return TokenValidateView;

});