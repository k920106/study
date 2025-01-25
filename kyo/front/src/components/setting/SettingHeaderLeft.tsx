import React from 'react';
import Ionicons from 'react-native-vector-icons/Ionicons';
import HeaderButton from '../common/HeaderButton';
import { colors } from '@/constants';
import { CompositeNavigationProp } from '@react-navigation/native';
import { StackNavigationProp } from '@react-navigation/stack';
import { SettingStackParamList } from '@/navigations/stack/SettingStackNavigator';
import { DrawerNavigationProp } from '@react-navigation/drawer';
import { MainDrawerParamList } from '@/navigations/drawer/MainDrawerNavigator';
import { StyleSheet } from 'react-native';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

type SettingHeaderLeftProps = CompositeNavigationProp<
    StackNavigationProp<SettingStackParamList>,
    DrawerNavigationProp<MainDrawerParamList>
>;

function SettingHeaderLeft(navigation: SettingHeaderLeftProps) {
    const { theme } = useThemeStore();
    const styles = styling(theme);

    return (
        <HeaderButton
            //icon={<Ionicons name="menu" color={colors.BLACK} size={25} />}
            icon={<Ionicons name="menu" color={colors[theme].BLACK} size={25} />}
            onPress={() => navigation.openDrawer()}
        />
    );
}

const styling = (theme: ThemeMode) =>
    StyleSheet.create({
        
    });

export default SettingHeaderLeft;