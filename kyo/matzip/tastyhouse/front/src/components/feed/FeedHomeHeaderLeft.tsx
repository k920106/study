import React from 'react';
import { StyleSheet } from 'react-native';
import HeaderButton from '../common/HeaderButton';
import { colors } from '@/constants';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { CompositeNavigationProp } from '@react-navigation/native';
import { StackNavigationProp } from '@react-navigation/stack';
import { DrawerNavigationProp } from '@react-navigation/drawer';
import { FeedStackParamList } from '@/navigations/stack/FeedStackNavigator';
import { MainDrawerParamList } from '@/navigations/drawer/MainDrawerNavigator';
import { ThemeMode } from '@/types';
import useThemeStore from '@/store/useThemeStore';

type FeedHomeHeaderLeftProps = CompositeNavigationProp<
    StackNavigationProp<FeedStackParamList>,
    DrawerNavigationProp<MainDrawerParamList>
>;

function FeedHomeHeaderLeft(navigation: FeedHomeHeaderLeftProps) {
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

//const styles = StyleSheet.create({});
const styling = (theme: ThemeMode) =>
    StyleSheet.create({
    });
export default FeedHomeHeaderLeft;