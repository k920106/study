import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import { LatLng } from 'react-native-maps';

import { colors, feedNavigations } from '@/constants';
import FeedHomeScreen from '@/screens/feed/FeedHomeScreen';
import FeedHomeHeaderLeft from '@/components/feed/FeedHomeHeaderLeft';
import EditPostScreen from '@/screens/feed/EditPostScreen';
import FeedDetailScreen from '@/screens/feed/FeedDetailScreen';
import ImageZoomScreen from '@/screens/feed/ImageZoomScreen';
import { ThemeMode } from '@/types';
import { StyleSheet } from 'react-native';
import useThemeStore from '@/store/useThemeStore';

export type FeedStackParamList = {
    [feedNavigations.FEED_HOME]: undefined;
    [feedNavigations.FEED_DETAIL]: { id: number };
    [feedNavigations.EDIT_POST]: { location: LatLng };
    [feedNavigations.IMAGE_ZOOM]: { index: number };
};

const Stack = createStackNavigator<FeedStackParamList>();

function FeedStackNavigator() {
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
                //headerTintColor: 'black',
                headerTintColor: colors[theme].BLACK,
            }}>
            <Stack.Screen
                name={feedNavigations.FEED_HOME}
                component={FeedHomeScreen}
                options={({ navigation }) => ({
                    headerTitle: '피드',
                    headerLeft: () => FeedHomeHeaderLeft(navigation),
                })}
            />
            <Stack.Screen
                name={feedNavigations.FEED_DETAIL}
                component={FeedDetailScreen}
                options={{
                    headerShown: false,
                    headerTitle: ' ',
                    cardStyle: {
                        //backgroundColor: colors.GRAY_100,
                        backgroundColor: colors[theme].GRAY_100,
                    },
                }}
            />
            <Stack.Screen
                name={feedNavigations.EDIT_POST}
                component={EditPostScreen}
                options={{
                    headerTitle: '장소 수정',
                }}
            />
            <Stack.Screen
                name={feedNavigations.IMAGE_ZOOM}
                component={ImageZoomScreen}
                options={{
                    headerTitle: ' ',
                    headerShown: false,
                }}
            />
        </Stack.Navigator>
    );
}

const styling = (theme: ThemeMode) =>
    StyleSheet.create({
    });

export default FeedStackNavigator;