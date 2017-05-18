import _ from "lodash";
import React from "react";
import withScriptjs from "react-google-maps/lib/async/withScriptjs";

import {
  withGoogleMap,
  GoogleMap,
  Marker,
} from "react-google-maps/lib";

const AsyncGoogleMap = _.flowRight(
  withScriptjs,
  withGoogleMap,
)(props => (
    <GoogleMap
        ref={props.onMapLoad}
        defaultZoom={5}
        defaultCenter={{ lat: -18.14584922288026, lng: -44.47265625 }}
        onClick={props.onMapClick}
    >
        {props.markers.map(marker => (
            <Marker
                {...marker}
                onRightClick={() => props.onMarkerRightClick(marker)}
            />
        ))}
    </GoogleMap>
));

export default class Map extends React.Component {

    constructor(){
        super()
        this.handleMapLoad = this.handleMapLoad.bind(this);
        this.handleMapClick = this.handleMapClick.bind(this);
        this.handleMarkerRightClick = this.handleMarkerRightClick.bind(this);
        this.state = {
            markers: [{
                position: {
                    lat: -18.14584922288026,
                    lng: -44.47265625,
                },
                key: 'Brazil',
                defaultAnimation: 2,
            }],
        }
    }

    handleMapLoad(map) {
        this._mapComponent = map;
        if (map) {
            map.getZoom()
        }
    }

    /*
    * This is called when you click on the map.
    * Go and try click now.
    */
    handleMapClick(event) {
        const nextMarkers = [
            ...this.state.markers,
            {
                position: {
                    lat: event.latLng.lat(),
                    lng: event.latLng.lng(),
                },
                defaultAnimation: 2,
                key: Date.now(),
            },
        ];
        this.setState({
            markers: nextMarkers,
        });
    }

    handleMarkerRightClick(targetMarker) {
        const nextMarkers = this.state.markers.filter(marker => marker !== targetMarker);
        this.setState({
            markers: nextMarkers,
        });
    }

    render() {
        return (
            <AsyncGoogleMap
                googleMapURL="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyCOOgFVb9s4w_cRWEhoAUKFE8n6zwIelPI"
                loadingElement={
                <div style={{ height: `100%` }}>
                    
                </div>
                }
                containerElement={
                <div style={{ position: 'absolute',
                                top: 200,
                                left: 150,
                                right: 150,
                                bottom: 200,
                                justifyContent: 'flex-end',
                                alignItems: 'center',}} />
                }
                mapElement={
                <div style={{ height: `100%` }} />
                }
                onMapLoad={this.handleMapLoad}
                onMapClick={this.handleMapClick}
                markers={this.state.markers}
                onMarkerRightClick={this.handleMarkerRightClick}
            />
        );
    }
}