define([
    'backbone'
], function(Backbone){

    var UserTokenModel = Backbone.Model.extend({

        urlRoot: 'usertoken',

        defaults: {
            token: '',
            username: '',
            tipo: '',
            fechaVigencia: (new Date()).getTime() + 5
            // fechaVigencia: (new Date()).getTime()
        },



        isAuthorized: function(){
          return Boolean(this.get("token"));
        },


        validation: {
            token: {
                required: true
            },
            username: {
                required: true,
                pattern: 'email'
            },
            tipo: {
                required: true
            },
            fechaVigencia: {
                required: true
            }
        }

    });

    return UserTokenModel;
});
