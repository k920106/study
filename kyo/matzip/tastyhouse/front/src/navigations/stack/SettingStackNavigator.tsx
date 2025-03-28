import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';

import { colors, settingNavigations } from '@/constants';
import SettingHomeScreen from '@/screens/setting/SettingHomeScreen';
import EditProfileScreen from '@/screens/setting/EditProfileScreen';
import SettingHeaderLeft from '@/components/setting/SettingHeaderLeft';
import DeleteAccountScreen from '@/screens/setting/DeleteAccountScreen';
import EditCategoryScreen from '@/screens/setting/EditCategoryScreen';
import { StyleSheet } from 'react-native';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

export type SettingStackParamList = {
    [settingNavigations.SETTING_HOME]: undefined;
    [settingNavigations.EDIT_PROFILE]: undefined;
    [settingNavigations.DELETE_ACCOUNT]: undefined;
    [settingNavigations.EDIT_CATEGORY]: undefined;
};

const Stack = createStackNavigator<SettingStackParamList>();

function SettingStackNavigator() {
    const { theme } = useThemeStore();
    const styles = styling(theme);
    
    return (
        <Stack.Navigator
            screenOptions={{
                cardStyle: {
                    //backgroundColor: colors.GRAY_100,
                    backgroundColor: colors[theme].GRAY_100,
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
                name={settingNavigations.SETTING_HOME}
                component={SettingHomeScreen}
                options={({ navigation }) => ({
                    headerTitle: '설정',
                    headerLeft: () => SettingHeaderLeft(navigation),
                })}
            />
            <Stack.Screen
                name={settingNavigations.EDIT_PROFILE}
                component={EditProfileScreen}
                options={{
                    headerTitle: '프로필 수정',
                    cardStyle: {
                        //backgroundColor: colors.WHITE,
                        backgroundColor: colors[theme].WHITE,
                    },
                }}
            />
            <Stack.Screen
                name={settingNavigations.DELETE_ACCOUNT}
                component={DeleteAccountScreen}
                options={{
                    headerTitle: '회원탈퇴',
                    cardStyle: {
                        //backgroundColor: colors.WHITE,
                        backgroundColor: colors[theme].WHITE,
                    },
                }}
            />
            <Stack.Screen
                name={settingNavigations.EDIT_CATEGORY}
                component={EditCategoryScreen}
                options={{
                    headerTitle: '카테고리 설정',
                    cardStyle: {
                        //backgroundColor: colors.WHITE,
                        backgroundColor: colors[theme].WHITE,
                    },
                }}
            />
        </Stack.Navigator>
    );
}

const styling = (theme: ThemeMode) =>
    StyleSheet.create({
    });

export default SettingStackNavigator;