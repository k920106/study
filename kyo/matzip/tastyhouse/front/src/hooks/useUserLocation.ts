import { useEffect, useState } from 'react';
import { LatLng } from 'react-native-maps';
import GeoLocation from '@react-native-community/geolocation';
import useAppState from './useAppState';

function useUserLocation() {
    const [userLocation, setUserLocation] = useState<LatLng>({
        latitude: 37.53049488620558,
        longitude: 127.12056863562661,
    });
    const [isUserLocationError, setIsUserLocationError] = useState(false);
    const {isComeback} = useAppState();

    useEffect(() => {
        GeoLocation.getCurrentPosition(
            info => {
                const { latitude, longitude } = info.coords;
                setUserLocation({ latitude, longitude });
                setIsUserLocationError(false);
            },
            () => {
                setIsUserLocationError(true);
            },
            {
                enableHighAccuracy: true,
            },
        );
    // }, []);
    }, [isComeback]);

    return { userLocation, isUserLocationError };
}

export default useUserLocation;