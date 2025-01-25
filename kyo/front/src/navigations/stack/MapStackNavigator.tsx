import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';

import MapHomeScreen from '@/screens/map/MapHomeScreen';
import { colors, mapNavigations } from '@/constants';
import AddPostScreen from '@/screens/map/AddPostScreen';
import { LatLng } from 'react-native-maps';
import SearchLocationScreen from '@/screens/map/SearchLocationScreen';
import { StyleSheet } from 'react-native';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

export type MapStackParamList = {
    [mapNavigations.MAP_HOME]: undefined;
    [mapNavigations.ADD_POST]: { location: LatLng };
    [mapNavigations.SEARCH_LOCATION]: undefined;
};

const Stack = createStackNavigator<MapStackParamList>();

function MapStackNavigator() {
    const { theme } = useThemeStore();
    const styles = styling(theme);
    
    return (
        <Stack.Navigator
            screenOptions={{
                cardStyle: {
                    //backgroundColor: 'white',
                    backgroundColor: colors[theme].WHITE,
                },
                headerStyle: {
                    shadowColor: 'gray',
                    //backgroundColor: 'white',
                    backgroundColor: colors[theme].WHITE,
                },
                headerTitleStyle: {
                    fontSize: 15,
                    color: colors[theme].BLACK,
                },
                // headerTintColor: 'black',
                headerTintColor: colors[theme].BLACK,
            }}>
            <Stack.Screen
                name={mapNavigations.MAP_HOME}
                component={MapHomeScreen}
                options={{
                    headerTitle: ' ',
                    headerShown: false,
                }}
            />
            <Stack.Screen
                name={mapNavigations.ADD_POST}
                component={AddPostScreen}
                options={{
                    headerTitle: '장소 추가',
                }}
            />
            <Stack.Screen
                name={mapNavigations.SEARCH_LOCATION}
                component={SearchLocationScreen}
                options={{
                    presentation: 'modal',
                    headerTitle: '장소 검색',
                }}
            />
        </Stack.Navigator>
    );
}

const styling = (theme: ThemeMode) =>
    StyleSheet.create({
    });

export default MapStackNavigator;