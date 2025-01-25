import React from 'react';
import { StyleSheet } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import { authNavigations, colors } from '@/constants';
import AuthHomeScreen from '@/screens/auth/AuthHomeScreen';
import LoginScreen from '@/screens/auth/LoginScreen';
import SignScreen from '@/screens/auth/SignupScreen';
import KakaoLoginScreen from '@/screens/auth/KakaoLoginScreen';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

export type AuthStackParamList = {
    [authNavigations.AUTH_HOME]: undefined;
    [authNavigations.LOGIN]: undefined;
    [authNavigations.SIGNUP]: undefined;
    [authNavigations.KAKAO]: undefined;
}

const Stack = createStackNavigator<AuthStackParamList>();

function AuthStackNavigator() {
    const { theme } = useThemeStore();
    const styles = styling(theme);
    
    return (
        <Stack.Navigator screenOptions={{
            cardStyle: {
                //backgroundColor: 'white'
                backgroundColor: colors[theme].WHITE,
            },
            headerStyle: {
                //backgroundColor: 'white',
                backgroundColor: colors[theme].WHITE,
                shadowColor: 'gray'
            },
            headerTitleStyle: {
                fontSize: 15,
                color: colors[theme].BLACK,
            },
            //headerTintColor: 'black'
            headerTintColor: colors[theme].BLACK,
        }}>
            <Stack.Screen name={authNavigations.AUTH_HOME}
                component={AuthHomeScreen}
                options={{
                    headerTitle: '',
                    headerShown: false
                }} />
            <Stack.Screen name={authNavigations.LOGIN}
                component={LoginScreen}
                options={{
                    headerTitle: '로그인'
                }} />
            <Stack.Screen name={authNavigations.SIGNUP}
                component={SignScreen}
                options={{
                    headerTitle: '회원가입'
                }} />
            <Stack.Screen
                name={authNavigations.KAKAO}
                component={KakaoLoginScreen}
                options={{
                    headerTitle: '카카오 로그인',
                }} />
        </Stack.Navigator>
    );
}

//const styles = StyleSheet.create({
const styling = (theme: ThemeMode) =>
    StyleSheet.create({
    });

export default AuthStackNavigator;