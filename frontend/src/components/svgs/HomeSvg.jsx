const HomeSvg = ({ diameter = 24, color = "var(--icon1)", ...props }) => {
    return (
        <svg
            width={diameter}
            height={diameter}
            viewBox={`0 0 24 24`}
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
            {...props}
        >
            <g id="COCO/Line/User">
                <path
                    d="M4 9.43243L12 3.5L20 9.43243V20H12H4V9.43243Z"
                    stroke={color}
                    strokeWidth="2"
                    strokeLinejoin="round"
                />
                <path
                    d="M12 20V14"
                    stroke={color}
                    strokeWidth="2"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                />
            </g>
        </svg>
    );
};

export default HomeSvg;
