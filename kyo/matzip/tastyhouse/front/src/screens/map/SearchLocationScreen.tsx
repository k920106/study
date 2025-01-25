import Pagination from '@/components/common/Pagination';
import SearchInput from '@/components/common/SearchInput';
import SearchRegionResult from '@/components/map/SearchRegionResult';
import useSearchLocation from '@/hooks/useSearchLocation';
import useUserLocation from '@/hooks/useUserLocation';
import useThemeStore from '@/store/useThemeStore';
import { ThemeMode } from '@/types';
import React, { useState } from 'react';
import { Keyboard, StyleSheet, View } from 'react-native';

function SearchLocationScreen() {
    const [keyword, setKeyword] = useState<string>('');
    const { userLocation } = useUserLocation();
    const { regionInfo, pageParam, fetchNextPage, fetchPrevPage, hasNextPage } = useSearchLocation(keyword, userLocation);
    const { theme } = useThemeStore();
    const styles = styling(theme);

    const handleChangeKeyword = (text: string) => {
        setKeyword(text);
    };

    return (
        <View style={styles.container}>
            <SearchInput
                autoFocus
                value={keyword}
                onChangeText={handleChangeKeyword}
                placeholder="검색할 장소를 입력하세요."
                onSubmit={() => Keyboard.dismiss()}
            />
            <SearchRegionResult regionInfo={regionInfo} />
            <Pagination
                pageParam={pageParam}
                fetchPrevPage={fetchPrevPage}
                fetchNextPage={fetchNextPage}
                hasNextPage={hasNextPage}
                totalLength={regionInfo.length}
            />
        </View>
    );
}

//const styles = StyleSheet.create({
const styling = (theme: ThemeMode) =>
    StyleSheet.create({
        container: {
            flex: 1,
            padding: 20,
        },
    });

export default SearchLocationScreen;