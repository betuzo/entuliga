define([
    'backbone',
    'session',
], function(Backbone, Session){

    var UserTokenModel = Backbone.Model.extend({

        urlRoot: 'usertoken',

        defaults: {
            token: '',
            username: '',
            tipo: '',
            fechaVigencia: (new Date()).getTime() + 5
            // fechaVigencia: (new Date()).getTime()
        },

        initialize: function(){

        },

        isAuthorized: function(){
          return Boolean(this.get("token"));
        },

        checkAuth: function( token , callback) {
          this.set({ id : token });
          this.set({ token : token });
          var thatCallback = callback
          this.fetch({
            success: function(mod, res){
              console.log("success token checkout");
            },
            error: function(mod, res){
              console.log("error token checkout");
            }
          })

          thatCallback();

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
