define([
    'jquery',
    'backbone',
    'bootstrap',
    'selecter',
    'fabric',
    'core/BaseView',
    'models/TorneoPartidoModel',
    'views/private/partido/PartidoEquipoView',
    'views/private/partido/PartidoArbitrosView',
    'views/private/partido/PartidoEditView',
    'views/private/partido/NewArbitroPartidoView',
    'views/private/estadistica/EstadisticaResumenView',
    'views/private/estadistica/EstadisticaPuntosView',
    'views/private/estadistica/EstadisticaFaltasView',
    'views/private/estadistica/EstadisticaMovimientosView',
    'views/private/estadistica/EstadisticaAsistenciasView',
    'views/private/estadistica/EstadisticaBloqueosView',
    'views/private/estadistica/EstadisticaRebotesView',
    'views/private/estadistica/EstadisticaRobosView',
    'views/private/estadistica/PuntoCreateView',
    'views/private/estadistica/FaltaCreateView',
    'views/private/estadistica/MovimientoCreateView',
    'views/private/estadistica/AsistenciaCreateView',
    'views/private/estadistica/BloqueoCreateView',
    'views/private/estadistica/ReboteCreateView',
    'views/private/estadistica/RoboCreateView',
    'views/private/util/ModalGenericView',
    'text!templates/private/partido/tplPartidoAdmin.html'
], function($, Backbone, bootstrap, selecter, fabriclib, BaseView, TorneoPartidoModel,
            PartidoEquipoView, PartidoArbitrosView, PartidoEditView, NewArbitroPartidoView,
            EstadisticaResumenView, EstadisticaPuntosView, EstadisticaFaltasView,
            EstadisticaMovimientosView, EstadisticaAsistenciasView,
            EstadisticaBloqueosView, EstadisticaRebotesView, EstadisticaRobosView,
            PuntoCreateView, FaltaCreateView, MovimientoCreateView, AsistenciaCreateView,
            BloqueoCreateView, ReboteCreateView, RoboCreateView, ModalGenericView,
            tplPartidoAdmin){

	var PartidoAdminView = BaseView.extend({
        template: _.template(tplPartidoAdmin),

        events: {
            'click #partido-editar'             : 'partidoEditar',
            'click #agregar-arbitro'            : 'agregarArbitro',
            'click #partido-comenzar'           : 'partidoComenzar',
            'click #partido-cancelar'           : 'partidoCancelar',
            'click #partido-finalizar'          : 'partidoFinalizar',

            'click #partido-puntos-local'       : 'partidoPuntosLocal',
            'click #partido-faltas-local'       : 'partidoFaltasLocal',
            'click #partido-cambios-local'      : 'partidoCambiosLocal',
            'click #partido-asistencias-local'  : 'partidoAsistenciasLocal',
            'click #partido-bloqueos-local'     : 'partidoBloqueosLocal',
            'click #partido-rebotes-local'      : 'partidoRebotesLocal',
            'click #partido-robos-local'        : 'partidoRobosLocal',

            'click #partido-puntos-visita'      : 'partidoPuntosVisita',
            'click #partido-faltas-visita'      : 'partidoFaltasVisita',
            'click #partido-cambios-visita'     : 'partidoCambiosVisita',
            'click #partido-asistencias-visita' : 'partidoAsistenciasVisita',
            'click #partido-bloqueos-visita'    : 'partidoBloqueosVisita',
            'click #partido-rebotes-visita'     : 'partidoRebotesVisita',
            'click #partido-robos-visita'       : 'partidoRobosVisita'
        },

        initialize: function(idPartido) {
            this.model = new TorneoPartidoModel();
            this.listenTo(this.model, 'sync', this.syncPartido);
        },

        render: function() {
            return this;
        },

        syncPartido: function() {
            this.$el.html(this.template(this.model.toJSON()));
            $('#estadistica-tab a').click(function (e) {
              e.preventDefault();
              $(this).tab('show');
            })
            this.$el.find('[data-toggle="tooltip"]').tooltip();

            this.strPath = 'M 192.3004,118.04878 H 196.63056 V 139.6996 C 196.63056,140.89553 197.59971,141.86468 198.79564,141.86468 H 216.1163 C 217.31223,141.86468 218.28137,140.89553 218.28137,139.6996 V 118.04878 H 222.61154 C 223.80747,118.04878 224.77662,117.07964 224.77662,115.88371 V 109.38846 C 224.77662,108.19252 223.80747,107.22338 222.61154,107.22338 H 213.95121 C 213.95121,110.81065 211.04324,113.71862 207.45597,113.71862 203.8687,113.71862 200.96072,110.81065 200.96072,107.22338 H 192.3004 C 191.10446,107.22338 190.13532,108.19252 190.13532,109.38846 V 115.88371 C 190.13532,117.07964 191.10446,118.04878 192.3004,118.04878 z';
            this.local = new PartidoEquipoView({modelo: this.model, parent: this, type: 'LOCAL'});
            this.visita = new PartidoEquipoView({modelo: this.model, parent: this, type: 'VISITA'});
            
            new PartidoArbitrosView({modelo:this.model, parent:this}).render();//render Marionette view
            new EstadisticaPuntosView({modelo: this.model, parent: this});
            new EstadisticaFaltasView({modelo: this.model, parent: this});
            new EstadisticaMovimientosView({modelo: this.model, parent: this});
            new EstadisticaAsistenciasView({modelo: this.model, parent: this});
            new EstadisticaBloqueosView({modelo: this.model, parent: this});
            new EstadisticaRebotesView({modelo: this.model, parent: this});
            new EstadisticaRobosView({modelo: this.model, parent: this});
            new EstadisticaResumenView(this.model);
            this.setUpCourt();
        },

        setIdPartido: function(idPartido) {
            this.model.set('id', idPartido);
            this.model.fetch();
        },

        partidoEditar: function() {
            new PartidoEditView({modelo: this.model, callbackAceptar: this.successSavePartido});
        },

        successSavePartido: function(partido) {

        },

        agregarArbitro: function() {
            new NewArbitroPartidoView({modelo: this.model, callbackAceptar: this.successSavePartido}).render().$el;
        },

        partidoPuntosLocal: function() {
            new PuntoCreateView({modelo: this.model, callbackAceptar: this.successAddPunto,
                                 origen: 'LOCAL', parent: this});
        },

        partidoPuntosVisita: function() {
            new PuntoCreateView({modelo: this.model, callbackAceptar: this.successAddPunto,
                                 origen: 'VISITA', parent: this});
        },

        successAddPunto: function(punto) {
            if (punto.get('origen') === 'LOCAL') {
                var puntoLocal = this.parent.model.get('localPuntos') + punto.get('tipoValor');
                this.parent.model.set({localPuntos: puntoLocal});
            } else {
                var puntoVisita = this.parent.model.get('visitaPuntos') + punto.get('tipoValor');
                this.parent.model.set({visitaPuntos: puntoVisita});
            }
            $('#section-estadisticas-resumen').html('');
            new EstadisticaResumenView(this.parent.model);
            app.puntosPartido.add(punto);
            this.destroyView();
        },

        successRemovePunto: function(punto) {
            if (punto.get('origen') === 'LOCAL') {
                var puntoLocal = this.model.get('localPuntos') - punto.get('tipoValor');
                this.model.set({localPuntos: puntoLocal});
            } else {
                var puntoVisita = this.model.get('visitaPuntos') - punto.get('tipoValor');
                this.model.set({visitaPuntos: puntoVisita});
            }
            $('#section-estadisticas-resumen').html('');
            new EstadisticaResumenView(this.model);
            app.puntosPartido.remove(punto);
        },

        partidoFaltasLocal: function() {
            new FaltaCreateView({modelo: this.model, callbackAceptar: this.successAddFalta,
                                             origen: 'LOCAL', parent: this});
        },

        partidoFaltasVisita: function() {
            new FaltaCreateView({modelo: this.model, callbackAceptar: this.successAddFalta,
                                             origen: 'VISITA', parent: this});
        },

        successAddFalta: function(falta) {
            app.faltasPartido.add(falta);
            this.destroyView();
        },

        successRemoveFalta: function(falta) {
            app.faltasPartido.remove(falta);
        },

        partidoCambiosLocal: function() {
            new MovimientoCreateView({modelo: this. model, callbackAceptar: this.successAddMovimiento,
                                              tipo: 'MANUAL', origen: 'LOCAL', parent: this});
        },

        partidoCambiosVisita: function() {
            new MovimientoCreateView({modelo: this.model, callbackAceptar: this.successAddMovimiento,
                                              tipo: 'MANUAL', origen: 'VISITA', parent: this});
        },

        partidoCambiosDirect: function(entraModel, saleModel, tipo, origen) {
            new MovimientoCreateView({modelo: this.model, callbackAceptar: this.successAddMovimiento,
                                tipo: tipo, origen: origen, parent: this, entra: entraModel, sale: saleModel});
        },

        successAddMovimiento: function(movimiento) {
            app.movimientosPartido.add(movimiento);
            this.destroyView();
        },


        successRemoveMovimiento: function(movimiento) {
            app.movimientosPartido.remove(movimiento);
        },

        partidoAsistenciasLocal: function() {
            new AsistenciaCreateView({modelo: this.model, callbackAceptar: this.successAddAsistencia,
                                        origen: 'LOCAL', parent: this});
        },

        partidoAsistenciasVisita: function() {
            new AsistenciaCreateView({modelo: this.model, callbackAceptar: this.successAddAsistencia,
                                        origen: 'VISITA', parent: this});
        },

        successAddAsistencia: function(asistencia) {
            app.asistenciasPartido.add(asistencia);
            this.destroyView();
        },

        successRemoveAsistencia: function(asistencia) {
            app.asistenciasPartido.remove(asistencia);
        },

        partidoBloqueosLocal: function() {
            new BloqueoCreateView({modelo: this.model, callbackAceptar: this.successAddBloqueo,
                                        origen: 'LOCAL', parent: this});

        },

        partidoBloqueosVisita: function() {
            new BloqueoCreateView({modelo: this.model, callbackAceptar: this.successAddBloqueo,
                                        origen: 'VISITA', parent: this});
        },

        successAddBloqueo: function(bloqueo) {
            app.bloqueosPartido.add(bloqueo);
            this.destroyView();
        },

        successRemoveBloqueo: function(bloqueo) {
            app.bloqueosPartido.remove(bloqueo);
        },

        partidoRebotesLocal: function() {
            new ReboteCreateView({modelo: this.model, callbackAceptar: this.successAddRebote,
                                        origen: 'LOCAL', parent: this});
        },

        partidoRebotesVisita: function() {
            new ReboteCreateView({modelo: this.model, callbackAceptar: this.successAddRebote,
                                        origen: 'VISITA', parent: this});
        },

        successAddRebote: function(rebote) {
            app.rebotesPartido.add(rebote);
            this.destroyView();
        },

        successRemoveRebote: function(rebote) {
            app.rebotesPartido.remove(rebote);
        },

        partidoRobosLocal: function() {
            new RoboCreateView({modelo: this.model, callbackAceptar: this.successAddRobo,
                                        origen: 'LOCAL', parent: this});
        },

        partidoRobosVisita: function() {
            new RoboCreateView({modelo: this.model, callbackAceptar: this.successAddRobo,
                                        origen: 'VISITA', parent: this});
        },

        successAddRobo: function(robo) {
            app.robosPartido.add(robo);
            this.destroyView();
        },

        successRemoveRobo: function(robo) {
            app.robosPartido.remove(robo);
        },

        partidoComenzar: function() {
            this.model.set({statusPartido: 'ENPROCESO'});
            this.saveModel();
        },

        partidoCancelar: function() {
            this.model.set({statusPartido: 'CANCELADO'});
            this.saveModel();
        },

        partidoFinalizar: function() {
            this.model.set({statusPartido: 'FINALIZADO'});
            this.saveModel();
        },

        saveModel: function() {
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({message: 'Partido registrado correctamente'});
                    },
                    error: function(model, error) {
                        new ModalGenericView({message: 'Se presento un error al registrar el partido'});
                    }
                });
            }
        },

        setUpCourt: function() {
            var pathBase = new fabric.Path(this.strPath, {});
            this.canvas = new fabric.Canvas('canvas');
            var position = {left: 0, top:0};
            this.isOver = false;
            var tolerancia = 20;

            this.canvas.setBackgroundImage('img/courtbasket.png', this.canvas.renderAll.bind(this.canvas), {
                backgroundImageOpacity: 0.5,
                backgroundImageStretch: false
            });

            var positionsLocalBase = [
                                        {left: 200, top:140, label:'B', type:0},
                                        {left: 160, top:120, label:'AP', type:0},
                                        {left: 170, top:180, label:'E', type:0},
                                        {left: 120, top:110, label:'A', type:0},
                                        {left: 45, top:175, label:'P', type:0},
                                        {left: this.canvas.width - pathBase.width - 200, top:140, label:'B', type:1},
                                        {left: this.canvas.width - pathBase.width - 160, top:120, label:'AP', type:1},
                                        {left: this.canvas.width - pathBase.width - 170, top:180, label:'E', type:1},
                                        {left: this.canvas.width - pathBase.width - 120, top:110, label:'A', type:1},
                                        {left: this.canvas.width - pathBase.width - 45, top:175, label:'P', type:1}
                                     ];

            this.paths = this.addPositionBase(positionsLocalBase);
            this.addStatistics();
            this.canvas.renderAll();

            var that = this;
            this.canvas.on('object:moving', function (e) {
                var pathTmp = that.paths;
                if (that.isOver){
                    pathTmp = [that.pathOver];
                }
                for (path in pathTmp) {
                    if ((e.target.typePosition === 2 && pathTmp[path].typePosition === 1) ||
                        (e.target.typePosition === 3 && pathTmp[path].typePosition === 0)){
                        continue;
                    }
                    that.pathOver = pathTmp[path];
                    if (((e.target.left > that.pathOver.left - tolerancia && e.target.left < that.pathOver.left + tolerancia) &&
                        (e.target.top > that.pathOver.top - tolerancia && e.target.top < that.pathOver.top + tolerancia)) ||
                         ((e.target.left > that.pathOver.left - tolerancia && e.target.left < that.pathOver.left + tolerancia) &&
                         (e.target.top > that.pathOver.top - tolerancia && e.target.top < that.pathOver.top + tolerancia)) ) {
                        that.pathOver.setOpacity(0.5);
                        that.pathOver.scale(1.2);
                        that.isOver = true;
                        if(that.pathOver.pathOver != null && that.pathOver.pathOver.player === e.target.player){
                            that.prevPathOver = that.pathOver;
                        }
                        break;
                    } else {
                        that.pathOver.setOpacity(1);
                        that.pathOver.scale(1);
                        that.isOver = false;
                    }
                }
            });

            this.canvas.on('mouse:down', function (e) {
                if (e.target !== undefined && e.target !== null) {
                    position.left = e.target.left;
                    position.top = e.target.top;
                }
            });
            var that = this;
            this.canvas.on('mouse:up', function (e) {
                if (e.target === undefined || e.target === null) {
                    return;
                }
                var entraModel = null;
                var saleModel = null;
                var tipo = null;
                var origen = null;
                if (that.isOver) {
                    tipo = 'DIRECT_INIT';
                    if (that.pathOver.pathOver !== null) {
                        if (that.prevPathOver != undefined && that.prevPathOver != null){
                            that.pathOver.pathOver.left = that.prevPathOver.left;
                            that.pathOver.pathOver.top = that.prevPathOver.top-15;
                            that.prevPathOver.set('pathOver', that.pathOver.pathOver);
                            that.prevPathOver = null;
                            tipo = null;
                        } else {
                            that.pathOver.pathOver.left = that.pathOver.pathOver.originLeft;
                            that.pathOver.pathOver.top = that.pathOver.pathOver.originTop;
                            saleModel = that.pathOver.pathOver.player;
                            tipo = 'DIRECT_OTHER';
                        }
                        that.pathOver.pathOver.setCoords();
                    }
                    e.target.set('left', that.pathOver.left);
                    e.target.set('top', that.pathOver.top-15);
                    entraModel = e.target.player;
                    origen = e.target.typeTeam;
                    that.pathOver.setOpacity(1);
                    that.pathOver.scale(1);
                    that.pathOver.set('pathOver', e.target);
                    that.isOver = false;
                } else {
                    e.target.set('left', position.left);
                    e.target.set('top', position.top);
                }
                e.target.setCoords();
                that.canvas.renderAll();
                if (tipo != null) {
                    that.partidoCambiosDirect(entraModel, saleModel, tipo, origen);
                }
            });
        },

        addPositionBase: function(positionsBase){
            var paths = [];
            for (pos in positionsBase) {
                var path = new fabric.Path(this.strPath, {
                    fill: 'rgba(0,0,0,0)',
                    stroke: 'black',
                    strokeWidth: 2,
                    originX: 'center',
                    originY: 'center'
                });
                var text = new fabric.Text(positionsBase[pos].label, {
                    fontSize: 15,
                    fill: "#000",
                    originX: 'center',
                    originY: 'center'
                });
                var group = new fabric.Group([ path, text ], {
                    left: positionsBase[pos].left,
                    top: positionsBase[pos].top,
                    height: path.height,
                    width: path.width,
                    hasRotatingPoint: false,
                    hasBorders: false,
                    hasControls: false,
                    selectable: false
                });
                group.item(0).left = 0;
                group.item(0).top = 0;
                group.item(1).left = 0;
                group.item(1).top = 4;
                group.set('typePosition', positionsBase[pos].type);
                group.set('pathOver', null);
                group.set('typeAction', 'POSITION_BASE');
                this.canvas.add(group);
                paths.push(group);
            }
            return paths;
        },

        addPlayers: function(players, type) {
            var space = 10;
            var pathBase = new fabric.Path(this.strPath, {});
            var total = Math.floor(this.canvas.width / 2 / (pathBase.width + space));
            var leftInit = ((this.canvas.width / 2) - ((total * pathBase.width) + ((total - 1) * space))) / 2;
            var color = this.model.get(type.toLowerCase() + 'Color');
            var top = 10;
            var typePosition = 2;
            if (type === 'VISITA') {
            	leftInit = leftInit + (this.canvas.width / 2);
            	typePosition = 3;
            }
            var left = leftInit;

            for (player in players.models) {
                if (parseInt(player) >= total && Math.floor((parseInt(player) + 1) % total) === 1){
                    left = leftInit;
                    top = top + pathBase.height + 10;
                }
                var path = new fabric.Path(this.strPath, {
                    fill: color,
                    stroke: "#000",
                    strokeWidth: 1,
                    originX: 'center',
                    originY: 'center'
                });
                var numero = new fabric.Text(players.models[player].get('numeroJugador'), {
                    fontSize: 25,
                    fill: "#000",
                    stroke: "#fff",
                    originX: 'center',
                    originY: 'center'
                });
                var alias = new fabric.Text(players.models[player].get('aliasJugador'), {
                    fontSize: 11,
                    fill: "#000",
                    originX: 'center',
                    originY: 'bottom'
                });
                var group = new fabric.Group([ path, numero, alias ], {
                    left: left,
                    top: top,
                    height: path.height + numero.height,
                    width: path.width,
                    hasRotatingPoint: false,
                    hasBorders: false,
                    hasControls: false
                });
                group.item(0).left = 0;
                group.item(0).top = 0;
                group.item(1).left = 0;
                group.item(1).top = 5;
                group.item(2).left = 0;
                group.item(2).top = numero.height + 5;
                group.set('typePosition',typePosition);
                group.set('typeTeam',type);
                group.set('originLeft', group.left);
                group.set('originTop', group.top);
                group.set('player', players.models[player]);
                group.set('typeAction', 'PLAYER');
                this.canvas.add(group);
                left = left + path.width + space;
            }
        },

        addStatistics: function() {
            var that = this;
            fabric.Image.fromURL('img/enceste-ok.png', function(oImg) {
                oImg.left = (that.canvas.width / 2) - oImg.width;
                oImg.top = that.canvas.height - oImg.height;
                oImg.hasRotatingPoint = false;
                oImg.hasBorders = false;
                oImg.hasControls = false;
                oImg.selectable = false;
                oImg.set('typeAction', 'ENCESTE');

                that.canvas.add(oImg);
                that.paths.push(oImg);
            });

            fabric.Image.fromURL('img/enceste-fail.png', function(oImg) {
                oImg.left = (that.canvas.width / 2);
                oImg.top = that.canvas.height - oImg.height;
                oImg.hasRotatingPoint = false;
                oImg.hasBorders = false;
                oImg.hasControls = false;
                oImg.selectable = false;
                oImg.set('typeAction', 'ENCESTE_FAIL');
                that.canvas.add(oImg);
                that.paths.push(oImg);
            });
        }
	});

	return PartidoAdminView;

});